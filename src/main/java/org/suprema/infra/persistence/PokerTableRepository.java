package org.suprema.infra.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.transaction.Transactional;
import org.suprema.infra.models.PokerTableModel;

public class PokerTableRepository implements PanacheRepository<PokerTableModel> {
    @Transactional
    public PokerTableModel create(PokerTableModel pokerTable) {
        persist(pokerTable);
        return pokerTable;
    }
}
