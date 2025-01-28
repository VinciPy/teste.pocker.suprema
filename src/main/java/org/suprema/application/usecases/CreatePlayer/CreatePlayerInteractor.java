package org.suprema.application.usecases.CreatePlayer;

import org.suprema.domain.entities.Player;

public class CreatePlayerInteractor {
    private PlayerGateway playerGateway;

    public CreatePlayerInteractor(PlayerGateway playerGateway) {
        this.playerGateway = playerGateway;

    }
    public Player createPlayer(Player player) {
        return this.playerGateway.createPlayer(player);
    }
}
