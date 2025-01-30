package org.suprema.infra.models;

import jakarta.persistence.*;

@Entity
public class PlayerModel {

    @Id
    @SequenceGenerator(
            name = "playerSeq",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String username;
    private String cpf;
    private String phone;
    private String password;

    @ManyToOne
    @JoinColumn(name = "poker_table_id")
    private PokerTableModel pokerTable;

    public PlayerModel(String username, String cpf, String phone, String password) {
        this.username = username;
        this.cpf = cpf;
        this.phone = phone;
        this.password = password;
    }

    public PlayerModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
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

    public PokerTableModel getPokerTable() {
        return pokerTable;
    }

    public void setPokerTable(PokerTableModel pokerTable) {
        this.pokerTable = pokerTable;
    }
}
