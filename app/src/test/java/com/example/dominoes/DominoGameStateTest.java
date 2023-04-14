package com.example.dominoes;

import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DominoGameStateTest {

    @Test
    public void rotatePlayer() {
        DominoGameState dgs = new DominoGameState();
        for(int i = 0; i < 4; i++) {
            int currPlayer = dgs.getCurrentPlayer();
            assertTrue(i == currPlayer);
            dgs.rotatePlayer();
        }
    }

    @Test
    public void removeDominoFromHand() {
        DominoGameState dgs = new DominoGameState();
        int currPlayer = dgs.getCurrentPlayer();
        ArrayList<Domino> hand = dgs.getPlayerHand(0);
        dgs.removeDominoFromHand(0, hand.get(0));
        assertTrue(hand.size() == 6);
    }

    @Test
    public void isValid() {
        DominoGameState dgs = new DominoGameState();
        int[][] board = dgs.getBoard();
        board[4][4] = 4;
        assertTrue(dgs.isValid(2, 4, 4, 1));
    }
}