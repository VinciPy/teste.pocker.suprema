package org.suprema.infra.controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.suprema.application.usecases.AddPlayerToTable.AddPlayerInteractor;
import org.suprema.application.usecases.CreatePlayer.PlayerGateway;
import org.suprema.application.usecases.CreatePokerTable.CreatePokerTableInteractor;
import org.suprema.application.usecases.CreatePokerTable.PokerTableGateway;
import org.suprema.application.usecases.SimulateWinner.SimulateWinnerInteractor;
import org.suprema.domain.entities.Player;
import org.suprema.infra.gateways.PlayerEntityMapper;
import org.suprema.infra.gateways.PlayerRepositoryGateway;
import org.suprema.infra.gateways.PokerTableRepositoryGateway;
import org.suprema.infra.persistence.PlayerRepository;
import org.suprema.infra.persistence.PokerTableRepository;
import org.suprema.infra.validations.PokerTableValidationDTO;
import org.suprema.infra.validations.UserValidationDTO;

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
    @POST
    public Response create(PokerTableValidationDTO data) {
        Set<ConstraintViolation<PokerTableValidationDTO>> violations = validator.validate(data);
        if (violations.isEmpty()) {
            this.createPokerTableInteractor.createPokerTable(this.pokerTableDTOMapper.toPokerTableDomain(data));
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
    @Path("/{tableId}/players")
    @POST
    public Response addPlayer(@PathParam("tableId") String tableId, AddPlayerDTOValidation data) {
        Set<ConstraintViolation<AddPlayerDTOValidation>> violations = validator.validate(data);
        if (violations.isEmpty()) {
            this.addPlayerInteractor.addPlayer(
                 data.userId,
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
    @POST
    public Response calculateWinner(@PathParam("tableId") Long tableId) {
        Player playerWinner = this.simulateWinnerInteractor.calculateWinner(tableId);
        return Response.status(200).entity(playerWinner).build();
    }

}
