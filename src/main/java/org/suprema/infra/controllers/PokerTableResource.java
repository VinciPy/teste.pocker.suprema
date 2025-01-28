package org.suprema.infra.controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.suprema.infra.validations.PokerTableValidationDTO;

@Path("/api/poker-tables")
public class PokerTableResource {
    public PokerTableResource(){

    }

    @RolesAllowed("Admin")
    @POST
    public void create(PokerTableValidationDTO data) {

    }

}
