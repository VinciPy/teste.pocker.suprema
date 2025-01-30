package org.suprema.infra.controllers.dto;

import org.suprema.domain.entities.PokerTable;
import org.suprema.infra.validations.PokerTableValidationDTO;

import java.util.Collections;

public class PokerTableDTOMapper {
    public PokerTable toPokerTableDomain(PokerTableValidationDTO pokerTableValidationDTO) {
        return new PokerTable(
                pokerTableValidationDTO.getName(),
                Collections.emptyList()
        );
    }
}
