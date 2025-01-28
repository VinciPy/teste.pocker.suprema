package org.suprema.infra.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.suprema.infra.models.PlayerModel;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<PlayerModel> {

    public PlayerModel create(PlayerModel playerModel) {
        persist(playerModel);
        PlayerModel saved = findById(playerModel.getId());
        return saved;
    }
}
