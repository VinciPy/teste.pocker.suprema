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
import org.suprema.application.usecases.CreatePokerTable.CreatePokerTableInteractor;
import org.suprema.domain.entities.Player;
import org.suprema.domain.entities.PokerTable;
import org.suprema.infra.gateways.PokerTableRepositoryGateway;

import java.util.ArrayList;
import java.util.Collections;

@QuarkusTest
public class PokerTableTest {
    @Mock
    private PokerTableRepositoryGateway pokerTableRepositoryGateway;

    @InjectMocks
    private CreatePokerTableInteractor createPokerTableInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should be a instance of poker table")
    public void testPokerTableEntity() {
        PokerTable pokerTable = new PokerTable(
                "test_1",
                new ArrayList<>()
        );
        Assertions.assertEquals(pokerTable.getClass(), PokerTable.class);
    }

    @Test
    @DisplayName("should be throw a expection of calculate winner")
    public void testPokerTableSetWinnerWithLess3Player(){
        try {
            PokerTable pokerTable = new PokerTable(
                    "test_1",
                    new ArrayList<>()
            );
            Player player1 = new Player(
                    "player1",
                    "111.111.111.111-11",
                    "23423423",
                    "12345"
            );
            pokerTable.addPlayer(player1);
            pokerTable.getWinner();
        } catch (IllegalStateException e) {
            Assertions.assertEquals(e.getMessage(), "Table must have at least 3 players");
        }
    }

    @Test
    @DisplayName("Should be test method of validate an invalid table")
    public void testMethodInvalidTable(){
        PokerTable pokerTable = new PokerTable(
                "test_1",
                new ArrayList<>()
        );
        Player player1 = new Player(
                "player1",
                "111.111.111.111-11",
                "23423423",
                "12345"
        );
        pokerTable.addPlayer(player1);
        Assertions.assertFalse(pokerTable.isValidTable());
    }

    @Test
    @DisplayName("Should be test of an calculate a valid winner")
    public void testMethodValidateAnWinner() {
        PokerTable pokerTable = new PokerTable(
                "test_1",
                new ArrayList<>()
        );
        Player player1 = new Player(
                "player1",
                "111.111.111.111-11",
                "23423423",
                "12345"
        );
        Player player2 = new Player(
                "player1",
                "111.111.111.111-11",
                "23423423",
                "12345"
        );
        Player player3 = new Player(
                "player1",
                "111.111.111.111-11",
                "23423423",
                "12345"
        );
        Player player4 = new Player(
                "player1",
                "111.111.111.111-11",
                "23423423",
                "12345"
        );
        pokerTable.addPlayer(player1);
        pokerTable.addPlayer(player2);
        pokerTable.addPlayer(player3);
        pokerTable.addPlayer(player4);
        Player winner = pokerTable.getWinner();
        Assertions.assertEquals(winner.getClass(), Player.class);
    }

    @Test
    @DisplayName("Should be test method of validate an valid table")
    public void testMethodValidateValidTable(){
        PokerTable pokerTable = new PokerTable(
                "test_1",
                new ArrayList<>()
        );
        Player player1 = new Player(
                "player1",
                "111.111.111.111-11",
                "23423423",
                "12345"
        );
        Player player2 = new Player(
                "player1",
                "111.111.111.111-11",
                "23423423",
                "12345"
        );
        Player player3 = new Player(
                "player1",
                "111.111.111.111-11",
                "23423423",
                "12345"
        );
        Player player4 = new Player(
                "player1",
                "111.111.111.111-11",
                "23423423",
                "12345"
        );
        pokerTable.addPlayer(player1);
        pokerTable.addPlayer(player2);
        pokerTable.addPlayer(player3);
        pokerTable.addPlayer(player4);
        Assertions.assertTrue(pokerTable.isValidTable());
    }

    @Test
    @DisplayName("Should be test a use case of create poker table")
    public void testUseCaseCreatePokerTable() {
        PokerTable pokerTableMock = new PokerTable(
                "pk_mock",
                new ArrayList<>()
        );
        Mockito.when(pokerTableRepositoryGateway.createPokerTable(Mockito.any(PokerTable.class)))
                .thenReturn(pokerTableMock);
        PokerTable result = this.createPokerTableInteractor.createPokerTable(pokerTableMock);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), pokerTableMock.getId());
    }


    @Test
    @DisplayName("Should be test a use case of add players different")
    public void testUseCaseAddPlayerDifferentsPokerTable() {
        PokerTable pokerTableMock = new PokerTable(
                "pk_mock",
                new ArrayList<>()
        );
        Player playerMock1 = new Player(
                "player_mock1",
                "111.111.111-23",
                "11 11111-1111",
                "12312"
        );
        Player playerMock2 = new Player(
                "player_mock1",
                "111.111.111-23",
                "11 11111-1111",
                "12312"
        );
        Mockito.when(pokerTableRepositoryGateway.createPokerTable(Mockito.any(PokerTable.class)))
                .thenReturn(pokerTableMock);
        PokerTable result = this.createPokerTableInteractor.createPokerTable(pokerTableMock);
        pokerTableMock.addPlayer(playerMock1);
        pokerTableMock.addPlayer(playerMock2);
        Assertions.assertEquals(result.getId(), pokerTableMock.getId());
    }
}
