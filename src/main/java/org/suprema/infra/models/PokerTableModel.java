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
    @SequenceGenerator(
            name = "tablePokerSeq",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    private String name;

    @OneToMany(mappedBy = "pokerTable", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<PlayerModel> players = new ArrayList<>();


    public PokerTableModel() {

    }
    public PokerTableModel(Long id, String name, List<PlayerModel> players) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
