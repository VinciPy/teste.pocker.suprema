package org.suprema.infra.controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.suprema.application.usecases.CreatePlayer.CreatePlayerInteractor;
import org.suprema.infra.gateways.PlayerEntityMapper;
import org.suprema.infra.gateways.PlayerRepositoryGateway;
import org.suprema.infra.persistence.PlayerRepository;
import org.suprema.infra.validations.UserValidationDTO;

import java.util.Set;

@Path("/api/users")
public class UserResource {
    private CreatePlayerInteractor createPlayerInteractor;
    private final PlayerDTOMapper playerDTOMapper;

    public UserResource() {
        this.createPlayerInteractor = new CreatePlayerInteractor(
                new PlayerRepositoryGateway(new PlayerRepository(), new PlayerEntityMapper())
        );
        this.playerDTOMapper = new PlayerDTOMapper();
    }

    @Inject
    Validator validator;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a player", description = "Player able to poker table")
    @APIResponse(
            responseCode = "200",
            description = "success",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @Schema(implementation = Result.class)
    @Transactional
    public Response create(UserValidationDTO player, @Context SecurityContext ctx) {
        Set<ConstraintViolation<UserValidationDTO>> violations = validator.validate(player);
        if (violations.isEmpty()) {
            this.createPlayerInteractor.createPlayer(this.playerDTOMapper.toPlayerDomain(player));
            return Response.status(201).build();
        } else {
            Result result = new Result(violations);
            return Response.status(400).entity(result).build();
        }
    }
}
