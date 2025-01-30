package org.suprema.infra.services;

import org.mindrot.jbcrypt.BCrypt;
import org.suprema.domain.entities.Player;
import org.suprema.infra.gateways.PlayerRepositoryGateway;

public class AuthService {
    private final PlayerRepositoryGateway playerRepositoryGateway;

    public AuthService(PlayerRepositoryGateway playerRepositoryGateway) {
        this.playerRepositoryGateway = playerRepositoryGateway;
    }

    public boolean validateUser(String username, String plainPassword) {
        Player user = this.playerRepositoryGateway.findByUsername(username);
        String passwordHashed = user.getPassword();
        boolean passwordValid = BCrypt.checkpw(plainPassword, passwordHashed);
        return passwordValid;
    }
}
