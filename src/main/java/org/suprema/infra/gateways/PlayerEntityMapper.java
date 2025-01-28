package org.suprema.infra.gateways;

import org.suprema.domain.entities.Player;
import org.suprema.infra.models.PlayerModel;

public class PlayerEntityMapper {
    PlayerModel toModel(Player playerDomainEntity) {
        return new PlayerModel(
                playerDomainEntity.getUsername(),
                playerDomainEntity.getCpf(),
                playerDomainEntity.getPhone(),
                playerDomainEntity.getPassword()
        );
    }

    Player toDomain(PlayerModel playerModelEntity) {
        return new Player(
            playerModelEntity.getUsername(),
            playerModelEntity.getCpf(),
            playerModelEntity.getPhone(),
            playerModelEntity.getPassword()
        );
    }
}
