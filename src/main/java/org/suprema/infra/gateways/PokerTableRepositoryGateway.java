package org.suprema.infra.gateways;

import org.suprema.application.usecases.CreatePokerTable.PokerTableGateway;
import org.suprema.domain.entities.PokerTable;
import org.suprema.infra.persistence.PokerTableRepository;

public class PokerTableRepositoryGateway implements PokerTableGateway {
    private PokerTableRepository pokerTableRepository;
    public PokerTableRepositoryGateway(PokerTableRepository pokerTableRepository) {
        this.pokerTableRepository = pokerTableRepository;
    }

    @Override
    public PokerTable createPokerTable(PokerTable pokerTable) {
        return this.pokerTableRepository.create(pokerTable);
    }
}
