package org.suprema.domain.entities;

import java.util.List;

public class PokerTable {
    private Long id;
    private String name;
    private List<Player> players;

    public PokerTable(String name, List<Player> players) {
        this.name = name;
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean addPlayer(Player player) {
        if (players.size() >= 8) {
            return false;
        }
        boolean playerAlreadyExists = players.contains(player);
        if (playerAlreadyExists) {
            throw new IllegalStateException("Player already in the tabe");
        }
        return this.players.add(player);
    }

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
