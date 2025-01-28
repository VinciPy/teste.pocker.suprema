package org.suprema.domain.entities;

import java.util.Set;

public class PokerTable {
    private Long id;
    private String name;
    private Set<Player> players;

    public PokerTable() {
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    // Método para adicionar um jogador à mesa
    public boolean addPlayer(Player player) {
        if (players.size() >= 8) {
            return false; // Mesa já cheia
        }
        return players.add(player);
    }

    // Verifica se a mesa tem no mínimo 3 jogadores
    public boolean isValidTable() {
        return players.size() >= 3;
    }

    public Player getWinner() {
        if (!isValidTable()) {
            throw new IllegalStateException("Table must have at least 3 players");
        }
        return players.stream()
                .skip((int) (Math.random() * players.size()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("There are no players at the table"));
    }
}
