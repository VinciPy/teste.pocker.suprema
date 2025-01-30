package org.suprema.application.usecases.CreatePlayer;

import org.mindrot.jbcrypt.BCrypt;
import org.suprema.domain.entities.Player;

import java.util.Optional;

public class CreatePlayerInteractor {
    private PlayerGateway playerGateway;

    public CreatePlayerInteractor(PlayerGateway playerGateway) {
        this.playerGateway = playerGateway;

    }
    public Player createPlayer(Player player) {
        Optional<Player> playerExistsWithUsername = this.playerGateway.findByUsername(player.getUsername());
        if (playerExistsWithUsername.isPresent()) {
            throw new IllegalStateException("Username is already used");
        }
        String plainPassword = player.getPassword();
        String passwordHashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        player.setPassword(passwordHashed);
        return this.playerGateway.createPlayer(player);
    }
}
