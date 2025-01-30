package org.suprema.application.usecases.CreatePlayer;

import org.suprema.domain.entities.Player;

import java.util.Optional;

public interface PlayerGateway {
    Player createPlayer(Player player);
    Optional<Player> findById(Long id);
    Player linkPokerTable(Player player, Long pokerTableId);
    Player save(Player player);
    Optional<Player> findByUsername(String username);
}
