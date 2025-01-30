package org.suprema.infra.controllers.dto;

import org.suprema.domain.entities.Player;
import org.suprema.infra.validations.UserValidationDTO;

public class PlayerDTOMapper {
    public Player toPlayerDomain(UserValidationDTO userDTO) {
        return new Player(
                userDTO.getUsername(),
                userDTO.getCpf(),
                userDTO.getPhone(),
                userDTO.getPassword()
        );
    }
}
