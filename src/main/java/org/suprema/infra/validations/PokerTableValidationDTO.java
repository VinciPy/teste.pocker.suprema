package org.suprema.infra.validations;

import jakarta.validation.constraints.NotBlank;

public class PokerTableValidationDTO {
    @NotBlank(message = "Name is Mandatory")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
