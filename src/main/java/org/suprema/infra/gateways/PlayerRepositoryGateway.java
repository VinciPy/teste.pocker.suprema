package org.suprema.infra.gateways;

import org.suprema.application.usecases.CreatePlayer.PlayerGateway;
import org.suprema.domain.entities.Player;
import org.suprema.infra.models.PlayerModel;
import org.suprema.infra.persistence.PlayerRepository;

import java.util.Optional;

public class PlayerRepositoryGateway implements PlayerGateway {
    private final PlayerRepository playerRepository;
    private final PlayerEntityMapper playerEntityMapper;

    public PlayerRepositoryGateway(PlayerRepository playerRepository, PlayerEntityMapper playerEntityMapper) {
        this.playerRepository = playerRepository;
        this.playerEntityMapper = playerEntityMapper;
    }

    @Override
    public Player createPlayer(Player player) {
        PlayerModel playerModelConverted = this.playerEntityMapper.toModel(player);
        PlayerModel created = this.playerRepository.create(playerModelConverted);
        Player entityConverted = this.playerEntityMapper.toDomain(created);
        return entityConverted;
    }

    @Override
    public Optional<Player> findById(Long id) {
        PlayerModel player = this.playerRepository.findById(id);
        return Optional.ofNullable(this.playerEntityMapper.toDomain(player));
    }
}
