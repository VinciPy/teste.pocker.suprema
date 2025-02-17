package org.suprema.infra.gateways;

import org.suprema.application.usecases.CreatePokerTable.PokerTableGateway;
import org.suprema.domain.entities.PokerTable;
import org.suprema.infra.models.PokerTableModel;
import org.suprema.infra.persistence.PokerTableRepository;

import java.util.Optional;

public class PokerTableRepositoryGateway implements PokerTableGateway {
    private PokerTableRepository pokerTableRepository;
    private PokerTableEntityMapper pokerTableEntityMapper;

    public PokerTableRepositoryGateway(PokerTableRepository pokerTableRepository) {
        this.pokerTableRepository = pokerTableRepository;
        this.pokerTableEntityMapper = new PokerTableEntityMapper(
                new PlayerEntityMapper()
        );
    }

    @Override
    public PokerTable createPokerTable(PokerTable pokerTable) {
        PokerTableModel pokerTableModel = this.pokerTableEntityMapper.toModel(pokerTable);
        PokerTableModel pokerTableSaved = this.pokerTableRepository.create(pokerTableModel);
        return this.pokerTableEntityMapper.toDomain(pokerTableSaved);
    }

    @Override
    public Optional<PokerTable> findById(Long id) {
        PokerTableModel pokerTableModel = this.pokerTableRepository.findById(id);
        return Optional.ofNullable(this.pokerTableEntityMapper.toDomain(pokerTableModel));
    }

    @Override
    public PokerTable save(PokerTable pokerTable) {
        PokerTableModel pokerTableSaved = this.pokerTableRepository.findById(pokerTable.getId());
        PokerTableModel pokerTableModel = this.pokerTableEntityMapper.toModel(pokerTable);
        pokerTableSaved.setPlayers(pokerTableModel.getPlayers());
        this.pokerTableRepository.persist(pokerTableSaved);
        return pokerTable;
    }
}
