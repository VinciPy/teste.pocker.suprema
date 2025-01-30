package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.suprema.domain.entities.Player;

@QuarkusTest
public class PlayerTest {
    @Test
    @DisplayName("Should be a instace of player")
    public void testPlayerEntity() {
        Player player = new Player(
                "test_username",
                "111.111.111-23",
                "11 11111-1111",
                "12312"
        );
        Assertions.assertEquals(player.getClass(), Player.class);
    }

}
