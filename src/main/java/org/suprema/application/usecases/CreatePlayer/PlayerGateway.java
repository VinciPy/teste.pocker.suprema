package org.suprema.application.usecases.CreatePlayer;

import org.suprema.domain.entities.Player;

public interface PlayerGateway {
    Player createPlayer(Player player);
}
