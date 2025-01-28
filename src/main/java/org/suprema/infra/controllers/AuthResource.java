package org.suprema.infra.controllers;

import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.Claims;
import org.suprema.constants.JWTIssuer;

import java.security.Key;
import java.util.Arrays;
import java.util.HashSet;

@Path("/api/auth")
public class AuthResource {

    @POST
    @Path("/login")
    @Produces("application/json")
    public Response login(AuthDTO authData) {

        String token = Jwt.issuer(JWTIssuer.SUPREMA.toString())
                        .upn(authData.getUsername())
                        .groups(new HashSet<>(Arrays.asList("Admin")))
                        .expiresIn(122200)
                        .sign();
        return Response.ok("{\"token\":\"" + token + "\"}").build();
    }
}
