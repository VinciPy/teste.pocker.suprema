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
    private String name;

}
