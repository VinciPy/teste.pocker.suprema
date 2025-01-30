package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.suprema.application.usecases.CreatePlayer.CreatePlayerInteractor;
import org.suprema.domain.entities.Player;
import org.suprema.infra.gateways.PlayerRepositoryGateway;

@QuarkusTest
public class PlayerTest {
    @Mock
    private PlayerRepositoryGateway playerRepositoryGateway;

    @InjectMocks
    private CreatePlayerInteractor createPlayerInteractor;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
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

    @Test
    @DisplayName("Should be create a user")
    public void testCreatePlayerUseCase() {
        Player playerMock = new Player(
                "test_username",
                "111.111.111-23",
                "11 11111-1111",
                "12312"
        );
        Mockito.when(playerRepositoryGateway.createPlayer(Mockito.any(Player.class)))
                .thenReturn(playerMock);
        Player result = this.createPlayerInteractor.createPlayer(playerMock);
        Assertions.assertEquals(result.getId(), playerMock.getId());
    }
}
