package org.suprema.infra.controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.suprema.application.usecases.CreatePokerTable.CreatePokerTableInteractor;
import org.suprema.infra.validations.PokerTableValidationDTO;

@Path("/api/poker-tables")
public class PokerTableResource {
    private final CreatePokerTableInteractor createPokerTableInteractor;

    @Inject
    Validator validator;
    public PokerTableResource(){
        this.createPokerTableInteractor = new CreatePokerTableInteractor(
                new PokE
        );
    }

    @RolesAllowed("Admin")
    @POST
    public void create(PokerTableValidationDTO data) {

    }

}
