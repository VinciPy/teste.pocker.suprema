package org.suprema.application.usecases.AddPlayerToTable;

import jakarta.ws.rs.NotFoundException;
import org.suprema.application.usecases.CreatePlayer.PlayerGateway;
import org.suprema.application.usecases.CreatePokerTable.PokerTableGateway;
import org.suprema.domain.entities.Player;
import org.suprema.domain.entities.PokerTable;

import java.util.Optional;

public class AddPlayerInteractor {
    private PokerTableGateway pokerTableGateway;
    private PlayerGateway playerGateway;

    public PokerTable addPlayer(Long playerId, Long tableId) {
        Optional<Player> playerExists = this.playerGateway.findById(playerId);
        if(playerExists.isEmpty()) {
            throw new NotFoundException("Player not exists");
        }
        Optional<PokerTable> pokerTableExists = this.pokerTableGateway.findById(tableId);
        if (pokerTableExists.isEmpty()) {
            throw new NotFoundException("Poker Table Id Invalid");
        }
        PokerTable pokerTable = pokerTableExists.get();
        pokerTable.addPlayer(playerExists.get());
        return pokerTable;
    }
}
