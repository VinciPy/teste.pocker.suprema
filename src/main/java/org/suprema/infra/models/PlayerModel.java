package org.suprema.infra.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class PlayerModel {

    @Id
    @SequenceGenerator(
            name = "playerSeq",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;
    private String username;
    private String cpf;
    private String phone;
    private String password;

    public PlayerModel(String username, String cpf, String phone, String password) {
        this.username = username;
        this.cpf = cpf;
        this.phone = phone;
        this.password = password;
    }
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
