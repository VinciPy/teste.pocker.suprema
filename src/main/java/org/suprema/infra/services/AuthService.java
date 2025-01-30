package org.suprema.infra.services;

import org.mindrot.jbcrypt.BCrypt;
import org.suprema.domain.entities.Player;
import org.suprema.infra.gateways.PlayerRepositoryGateway;

import java.util.Optional;

public class AuthService {
    private final PlayerRepositoryGateway playerRepositoryGateway;

    public AuthService(PlayerRepositoryGateway playerRepositoryGateway) {
        this.playerRepositoryGateway = playerRepositoryGateway;
    }

    public boolean validateUser(String username, String plainPassword) {
        Optional<Player> userExists = this.playerRepositoryGateway.findByUsername(username);
        if (userExists.isPresent()) {
            Player user = userExists.get();
            String passwordHashed = user.getPassword();
            boolean passwordValid = BCrypt.checkpw(plainPassword, passwordHashed);
            return passwordValid;
        }
        return false;
    }
}
