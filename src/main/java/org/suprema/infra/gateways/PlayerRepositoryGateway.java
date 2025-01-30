package org.suprema.infra.gateways;

import org.suprema.application.usecases.CreatePlayer.PlayerGateway;
import org.suprema.domain.entities.Player;
import org.suprema.infra.models.PlayerModel;
import org.suprema.infra.persistence.PlayerRepository;

import java.util.Optional;

public class PlayerRepositoryGateway implements PlayerGateway {
    private final PlayerRepository playerRepository;
    private final PlayerEntityMapper playerEntityMapper;

    private final PokerTableEntityMapper pokerTableEntityMapper;

    public PlayerRepositoryGateway(PlayerRepository playerRepository, PlayerEntityMapper playerEntityMapper) {
        this.playerRepository = playerRepository;
        this.playerEntityMapper = playerEntityMapper;
        this.pokerTableEntityMapper = new PokerTableEntityMapper(playerEntityMapper);
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

    @Override
    public Player linkPokerTable(Player player, Long pokerTableId) {
        return null;
    }

    @Override
    public Player save(Player player) {
        PlayerModel playerSaved = this.playerRepository.findById(player.getId());
        PlayerModel playerModel = this.playerEntityMapper.toModel(player);
        playerSaved.setPokerTable(this.pokerTableEntityMapper.toModel(player.getPokerTable()));
        this.playerRepository.persist(playerSaved);
        return this.playerEntityMapper.toDomain(playerSaved);
    }
}
