package org.suprema.application.usecases.SimulateWinner;

import jakarta.ws.rs.NotFoundException;
import org.suprema.application.usecases.CreatePokerTable.PokerTableGateway;
import org.suprema.domain.entities.Player;
import org.suprema.domain.entities.PokerTable;

import java.util.Optional;

public class SimulateWinnerInteractor {
    public final PokerTableGateway pokerTableGateway;

    public SimulateWinnerInteractor(PokerTableGateway pokerTableGateway) {
        this.pokerTableGateway = pokerTableGateway;
    }

    public Player calculateWinner(Long tableId) {
        Optional<PokerTable> pokerTableExists = this.pokerTableGateway.findById(tableId);
        if (pokerTableExists.isEmpty()) {
            throw new NotFoundException();
        }
        PokerTable pokerTable = pokerTableExists.get();
        Player winner = pokerTable.getWinner();
        return winner;
    }
}
