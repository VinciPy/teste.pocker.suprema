package org.suprema.infra.controllers;

import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claims;
import org.suprema.constants.JWTIssuer;
import org.suprema.infra.gateways.PlayerEntityMapper;
import org.suprema.infra.gateways.PlayerRepositoryGateway;
import org.suprema.infra.persistence.PlayerRepository;
import org.suprema.infra.services.AuthService;

import java.security.Key;
import java.util.Arrays;
import java.util.HashSet;

@Path("/api/auth")
public class AuthResource {

    private final AuthService authService;

    public AuthResource() {
        PlayerRepository playerRepository = new PlayerRepository();
        PlayerEntityMapper playerEntityMapper = new PlayerEntityMapper();
        PlayerRepositoryGateway playerRepositoryGateway = new PlayerRepositoryGateway(playerRepository, playerEntityMapper);
        this.authService = new AuthService(playerRepositoryGateway);
    }

    @POST
    @Path("/login")
    @Produces("application/json")
    public Response login(AuthDTO authData) {
        try {
            boolean validUser = this.authService.validateUser(authData.getUsername(), authData.getPassword());
            if (validUser == false) {
                return Response.status(400).build();
            }
            String token = Jwt.issuer(JWTIssuer.SUPREMA.toString())
                    .upn(authData.getUsername())
                    .groups(new HashSet<>(Arrays.asList("Admin")))
                    .expiresIn(122200)
                    .sign();
            return Response.ok("{\"token\":\"" + token + "\"}").build();
        } catch (NotFoundException e) {
           return Response.status(400).entity(new Result(
                   "User not found"
           )).build();
        }
    }
}
