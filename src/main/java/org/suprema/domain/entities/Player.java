package org.suprema.domain.entities;

public class Player {
    private Long id;
    private String username;
    private String name;
    private String cpf;
    private String phone;
    private String password;

    private PokerTable pokerTable;

    public PokerTable getPokerTable() {
        return pokerTable;
    }

    public void setPokerTable(PokerTable pokerTable) {
        if (this.pokerTable != null) {
           throw new IllegalStateException("User is already in Poker Table");
        }
        this.pokerTable = pokerTable;
    }

    public Player(String username, String cpf, String phone, String password) {
        this.id = id;
        this.username = username;
        this.cpf = cpf;
        this.phone = phone;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id;
    }
}
