package org.suprema.infra.gateways;

import org.suprema.domain.entities.Player;
import org.suprema.domain.entities.PokerTable;
import org.suprema.infra.models.PlayerModel;
import org.suprema.infra.models.PokerTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PokerTableEntityMapper {
    public final PlayerEntityMapper playerEntityMapper;

    public PokerTableEntityMapper(PlayerEntityMapper playerEntityMapper) {
        this.playerEntityMapper = playerEntityMapper;
    }

    public PokerTable toDomain(PokerTableModel pokerTableModel) {
//        List<Player> players = pokerTableModel.getPlayers()
//                .stream()
//                .map(player -> this.playerEntityMapper.toDomain(player))
//                .collect(Collectors.toList());
        PokerTable pokerTable = new PokerTable(
                pokerTableModel.getName(),
                new ArrayList<>()
        );
        pokerTable.setId(pokerTableModel.getId());
        return pokerTable;
    };

    public PokerTableModel toModel(PokerTable pokerTable) {
//        List<PlayerModel> playersModel = pokerTable.getPlayers()
//                .stream()
//                .map(player -> this.playerEntityMapper.toModel(player))
//                .collect(Collectors.toList());
        PokerTableModel pokerTableModel = new PokerTableModel(
                pokerTable.getId(),
                pokerTable.getName(),
                new ArrayList<>()
        );
        return pokerTableModel;
    };
}
