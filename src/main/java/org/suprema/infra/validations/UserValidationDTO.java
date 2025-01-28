package org.suprema.infra.validations;

import jakarta.validation.constraints.NotBlank;

public class UserValidationDTO {
    @NotBlank(message = "Username is mandatory")
    String username;

    @NotBlank(message = "CPF is mandatory")
    String cpf;

    @NotBlank(message = "phone is mandatory")
    String phone;

    @NotBlank(message = "password is necessary to logon")
    String password;

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }
}
