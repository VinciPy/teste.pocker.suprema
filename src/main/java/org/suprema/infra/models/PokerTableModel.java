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

}
