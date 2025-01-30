package org.suprema.infra.gateways;

import org.suprema.domain.entities.Player;
import org.suprema.infra.models.PlayerModel;

public class PlayerEntityMapper {
    private final PokerTableEntityMapper pokerTableEntityMapper;

    public PlayerEntityMapper() {
        this.pokerTableEntityMapper = new PokerTableEntityMapper(
                this
        );
    }

    PlayerModel toModel(Player playerDomainEntity) {
        PlayerModel playerModel =  new PlayerModel(
                playerDomainEntity.getUsername(),
                playerDomainEntity.getCpf(),
                playerDomainEntity.getPhone(),
                playerDomainEntity.getPassword()
        );
        playerModel.setId(playerDomainEntity.getId());
//        if (playerDomainEntity.getPokerTable() != null) {
//            playerModel.setPokerTable(this.pokerTableEntityMapper.toModel(playerDomainEntity.getPokerTable()));
//        }
        return playerModel;
    }

    Player toDomain(PlayerModel playerModelEntity) {
        Player player = new Player(
            playerModelEntity.getUsername(),
            playerModelEntity.getCpf(),
            playerModelEntity.getPhone(),
            playerModelEntity.getPassword()
        );
        player.setId(playerModelEntity.getId());
        if (playerModelEntity.getPokerTable() != null) {
            player.setPokerTable(this.pokerTableEntityMapper.toDomain(playerModelEntity.getPokerTable()));
        }
        return player;
    }
}
