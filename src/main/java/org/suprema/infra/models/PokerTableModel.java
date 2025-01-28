package org.suprema.infra.models;

import jakarta.persistence.*;
import org.suprema.domain.entities.Player;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PokerTableModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @OneToMany(mappedBy = "pokerTable", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<PlayerModel> players = new ArrayList<>();


    public PokerTableModel() {

    }
    public PokerTableModel(Long id, List<PlayerModel> players) {
        this.id = id;
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PlayerModel> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerModel> players) {
        this.players = players;
    }
}
