package com.example.dominoes;

import static org.junit.Assert.*;

import org.junit.Test;

public class DominoGameStateTest {

    @Test
    public void setBoardSlot() throws Exception {
        DominoGameState dgs = new DominoGameState();
        int[][] newBoard;
        newBoard = dgs.getBoard();
        newBoard[0][0] = 6;
        dgs.setBoardSlot(0,0,6);
        assertArrayEquals(newBoard,(dgs.getBoard()));
    }

    @Test
    public void getCurrentPlayer() throws Exception {
        DominoGameState dgs = new DominoGameState();
        int newCurrentPlayer = 0;
        int oldCurrentPlayer = dgs.getCurrentPlayer();
        assertEquals(newCurrentPlayer,oldCurrentPlayer);

    }

    @Test
    public void placeDomino() throws Exception {
        DominoGameState dgs = new DominoGameState();
        int playerIndx = 0;
        Domino domino1 = new Domino(6,6);
        int row = 0;
        int col = 1;
//        int[][] newBoard = dgs.getBoard();
//        newBoard[row][col-1] = 6;
//        newBoard[row][col] = 5;
        dgs.placeDomino(playerIndx, domino1, 0, row,(col-1),row, col);

        assertEquals(6, dgs.getBoard()[0][0]);
        assertEquals(6,dgs.getBoard()[0][1]);
    }
}