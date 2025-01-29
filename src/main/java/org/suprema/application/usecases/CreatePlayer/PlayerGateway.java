package org.suprema.application.usecases.CreatePlayer;

import org.suprema.domain.entities.Player;

import java.util.Optional;

public interface PlayerGateway {
    Player createPlayer(Player player);
    Optional<Player> findById(Long id);
}
