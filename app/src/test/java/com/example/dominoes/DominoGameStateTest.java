package com.example.dominoes;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class DominoGameStateTest {//dummy comment for push

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
        Domino domino1 = new Domino(6, 6);
        dgs.placeDomino(0, domino1, 0, 0, (0), 0, 1);
        assertEquals(6, dgs.getBoard()[0][0]);
        assertEquals(6, dgs.getBoard()[0][1]);
    }

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

    @Test
    public void getBoard(){
        DominoGameState dgs = new DominoGameState();
        int[][] board = dgs.getBoard();
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board.length; col++){
                board[row][col] = -1;
            }
        }
        assertArrayEquals(board, dgs.getBoard());
    }

    @Test
    public void getPlayerHand(){
        DominoGameState dgs = new DominoGameState();
        ArrayList<Domino> playerHand = new ArrayList<Domino>();
        ArrayList<Domino> tiles= new ArrayList<>();
        for(int i = 6; i > -1; i--) {
            for(int j = i; j > -1; j--) {
                Domino interm = new Domino(i,j);
                tiles.add(interm);
            }
        }
        for(int i = 0;i < 7; i++){
            playerHand.add(tiles.remove(0));
        }
    }
}