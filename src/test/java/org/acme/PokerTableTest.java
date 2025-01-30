package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.suprema.domain.entities.Player;
import org.suprema.domain.entities.PokerTable;

import java.util.ArrayList;
import java.util.Collections;

@QuarkusTest
public class PokerTableTest {
    @Test
    @DisplayName("should be a instace of poker table")
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
}
