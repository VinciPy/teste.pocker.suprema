package org.suprema.infra.validations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserValidationDTO {
    @NotBlank(message = "Username is mandatory")
    String username;

    @NotBlank(message = "CPF is mandatory")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF must be in the format XXX.XXX.XXX-XX")

    String cpf;

    @NotBlank(message = "phone is mandatory")
    @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$", message = "Phone must be in the format (XX) XXXX-XXXX or (XX) XXXXX-XXXX")
    String phone;

    @NotBlank(message = "password is necessary to logon")
    @Size(min = 5, message = "Password too short")
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
