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
import org.suprema.application.usecases.CreatePokerTable.CreatePokerTableInteractor;
import org.suprema.infra.gateways.PokerTableRepositoryGateway;
import org.suprema.infra.persistence.PokerTableRepository;
import org.suprema.infra.validations.PokerTableValidationDTO;
import org.suprema.infra.validations.UserValidationDTO;

import java.util.Set;

@Path("/api/poker-tables")
public class PokerTableResource {
    private final CreatePokerTableInteractor createPokerTableInteractor;
    private final AddPlayerInteractor addPlayerInteractor;
    private final PokerTableDTOMapper pokerTableDTOMapper;

    @Inject
    Validator validator;
    public PokerTableResource(){
        this.createPokerTableInteractor = new CreatePokerTableInteractor(
                new PokerTableRepositoryGateway(
                     new PokerTableRepository()
                )
        );
        this.addPlayerInteractor = new AddPlayerInteractor();
        this.pokerTableDTOMapper = new PokerTableDTOMapper();
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
    public Response addPlayer(@PathParam("tableId") Long tableId, AddPlayerDTOValidation data) {
        Set<ConstraintViolation<AddPlayerDTOValidation>> violations = validator.validate(data);
        if (violations.isEmpty()) {
            this.addPlayerInteractor.addPlayer(
                 data.userId,
                 tableId
            );
            return Response.status(201).build();
        } else {
            Result result = new Result(violations);
            return Response.status(400).entity(result).build();
        }
    }

}
