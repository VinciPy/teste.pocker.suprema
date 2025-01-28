package org.suprema.application.usecases.CreatePlayer;

import org.mindrot.jbcrypt.BCrypt;
import org.suprema.domain.entities.Player;

public class CreatePlayerInteractor {
    private PlayerGateway playerGateway;

    public CreatePlayerInteractor(PlayerGateway playerGateway) {
        this.playerGateway = playerGateway;

    }
    public Player createPlayer(Player player) {
        String plainPassword = player.getPassword();
        String passwordHashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        player.setPassword(passwordHashed);
        return this.playerGateway.createPlayer(player);
    }
}
