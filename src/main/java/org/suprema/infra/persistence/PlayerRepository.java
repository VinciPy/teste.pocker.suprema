package org.suprema.infra.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.suprema.infra.models.PlayerModel;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<PlayerModel> {

    @Transactional()
    public PlayerModel create(PlayerModel playerModel) {
        persist(playerModel);
        return playerModel;
    }
}
