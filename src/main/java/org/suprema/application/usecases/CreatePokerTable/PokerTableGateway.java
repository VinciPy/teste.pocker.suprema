package org.suprema.application.usecases.CreatePokerTable;

import org.suprema.domain.entities.PokerTable;

public interface PokerTableGateway {
    PokerTable createPokerTable(PokerTable pokerTable);
}
