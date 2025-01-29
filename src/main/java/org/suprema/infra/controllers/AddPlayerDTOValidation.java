package org.suprema.infra.controllers;

import jakarta.validation.constraints.NotBlank;

public class AddPlayerDTOValidation {
    @NotBlank(message = "UserId is mandatory")
    Long userId;
}
