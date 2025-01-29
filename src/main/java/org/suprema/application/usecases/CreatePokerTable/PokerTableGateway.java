package org.suprema.application.usecases.CreatePokerTable;

import org.suprema.domain.entities.PokerTable;

import java.util.Optional;

public interface PokerTableGateway {
    PokerTable createPokerTable(PokerTable pokerTable);
    Optional<PokerTable> findById(Long id);
}
