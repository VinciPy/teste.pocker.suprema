package org.suprema.application.usecases.CreatePokerTable;

import org.suprema.domain.entities.PokerTable;

public class CreatePokerTableInteractor {
    private PokerTableGateway pokerTableGateway;

    public CreatePokerTableInteractor(PokerTableGateway pokerTableGateway) {
        this.pokerTableGateway = pokerTableGateway;
    }

    public PokerTable createPokerTable(PokerTable pokerTable) {
        return this.pokerTableGateway.createPokerTable(pokerTable);
    }
}
