package org.suprema.infra.validations;

import jakarta.validation.constraints.NotNull;

public class AddPlayerDTOValidation {
    @NotNull(message = "UserId is mandatory")
    Long userId;

    public Long getUserId() {
        return userId;
    }
}
