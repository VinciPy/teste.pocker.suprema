package org.suprema.infra.controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.suprema.application.usecases.AddPlayerToTable.AddPlayerInteractor;
import org.suprema.application.usecases.CreatePlayer.PlayerGateway;
import org.suprema.application.usecases.CreatePokerTable.CreatePokerTableInteractor;
import org.suprema.application.usecases.CreatePokerTable.PokerTableGateway;
import org.suprema.application.usecases.SimulateWinner.SimulateWinnerInteractor;
import org.suprema.domain.entities.Player;
import org.suprema.domain.entities.PokerTable;
import org.suprema.infra.controllers.dto.PokerTableDTOMapper;
import org.suprema.infra.controllers.response.CreatePokerTableResponse;
import org.suprema.infra.controllers.response.PlayerWinnerReponse;
import org.suprema.infra.controllers.response.Result;
import org.suprema.infra.gateways.PlayerEntityMapper;
import org.suprema.infra.gateways.PlayerRepositoryGateway;
import org.suprema.infra.gateways.PokerTableRepositoryGateway;
import org.suprema.infra.persistence.PlayerRepository;
import org.suprema.infra.persistence.PokerTableRepository;
import org.suprema.infra.validations.AddPlayerDTOValidation;
import org.suprema.infra.validations.PokerTableValidationDTO;

import java.util.Set;

@Path("/api/poker-tables")
public class PokerTableResource {
    private final CreatePokerTableInteractor createPokerTableInteractor;
    private final AddPlayerInteractor addPlayerInteractor;
    private final PokerTableDTOMapper pokerTableDTOMapper;
    private final SimulateWinnerInteractor simulateWinnerInteractor;

    @Inject
    Validator validator;
    public PokerTableResource(){
        PokerTableGateway pokerTableGateway = new PokerTableRepositoryGateway(
                new PokerTableRepository()
        );
        PlayerGateway playerGateway = new PlayerRepositoryGateway(
                new PlayerRepository(),
                new PlayerEntityMapper()
        );
        this.createPokerTableInteractor = new CreatePokerTableInteractor(pokerTableGateway);
        this.addPlayerInteractor = new AddPlayerInteractor(
                pokerTableGateway,
                playerGateway
        );
        this.pokerTableDTOMapper = new PokerTableDTOMapper();
        this.simulateWinnerInteractor = new SimulateWinnerInteractor(pokerTableGateway);
    }

    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Operation(summary = "Create a poker table", description = "Create a poker table with a name")
    @APIResponse(
            responseCode = "200",
            description = "success",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Schema(implementation = CreatePokerTableResponse.class)
    @POST
    public Response create(PokerTableValidationDTO data) {
        Set<ConstraintViolation<PokerTableValidationDTO>> violations = validator.validate(data);
        if (violations.isEmpty()) {
            PokerTable pokerTable = this.createPokerTableInteractor.createPokerTable(this.pokerTableDTOMapper.toPokerTableDomain(data));
            System.out.println(pokerTable.getId() + "-----");
            CreatePokerTableResponse reponse = new CreatePokerTableResponse(String.valueOf(pokerTable.getId()));
            return Response.ok(reponse).status(201).build();
        } else {
            Result result = new Result(violations);
            return Response.status(400).entity(result).build();
        }
    }


    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{tableId}/players")
    @Operation(summary = "Add player", description = "Add A Player to poker table with id specific")
    @APIResponse(
            responseCode = "200",
            description = "success",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @POST
    public Response addPlayer(@PathParam("tableId") String tableId, AddPlayerDTOValidation data) {
        Set<ConstraintViolation<AddPlayerDTOValidation>> violations = validator.validate(data);
        if (violations.isEmpty()) {
            this.addPlayerInteractor.addPlayer(
                 data.getUserId(),
                 Long.valueOf(tableId)
            );
            return Response.status(201).build();
        } else {
            Result result = new Result(violations);
            return Response.status(400).entity(result).build();
        }
    }


    @RolesAllowed("Admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{tableId}/winner")
    @Operation(summary = "Calculate a winner", description = "Return an simulation of winner of table")
    @APIResponse(
            responseCode = "200",
            description = "success",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Schema(implementation = PlayerWinnerReponse.class)
    @POST
    public Response calculateWinner(@PathParam("tableId") Long tableId) {
        Player playerWinner = this.simulateWinnerInteractor.calculateWinner(tableId);
        PlayerWinnerReponse playerWinnerReponse = new PlayerWinnerReponse(
                playerWinner.getUsername(),
                playerWinner.getId()
        );
        return Response.status(200).entity(playerWinnerReponse).build();
    }

}
