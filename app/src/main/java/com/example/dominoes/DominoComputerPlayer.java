package com.example.dominoes;

import com.example.game.GameComputerPlayer;
import com.example.game.infoMsg.GameInfo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * AI for Domino's
 *
 * @author Alejandro Varela
 * @author Brit Dannen
 * @author Jackson Smith
 *
 */
public class DominoComputerPlayer extends GameComputerPlayer {
    DominoGameState dgs;
    private final int EMPTY = -1;
    public DominoComputerPlayer(String initName) {super(initName);}

    @Override
    protected void receiveInfo(GameInfo info) {
        dgs = new DominoGameState((DominoGameState)info);
        int[][] board = dgs.getBoard();
        ArrayList<Domino> hand = dgs.getPlayerHand(playerNum);

        if (dgs.getCurrentPlayer() != playerNum) {
            return;
        } //return
        Random rnd = new Random();
        if (rnd.nextBoolean()) {
            sleep(2000);
            DominoPassAction dpsa = new DominoPassAction(this);
            this.game.sendAction(dpsa);
        } else {
            sleep(2000);
            int[] stuff = aiMove(board, hand);
            DominoPlaceAction dpa = new DominoPlaceAction(this, stuff[4], stuff[0], stuff[1], stuff[2], stuff[3]);
            this.game.sendAction(dpa);
        }
    }


    protected int[] aiMove(int[][] board, ArrayList<Domino> hand) {
        int side1 = EMPTY;
        int side2 = EMPTY;
        int[] location = new int[5];
        location[0] = EMPTY;
        location[1] = EMPTY;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                for(int x = 0; x < hand.size();x++) {
                    side1 = hand.get(x).getEnd1();
                    side2 = hand.get(x).getEnd2();
                    if(dgs.isValid(i,j,side1,1)) {
                        location[0] = i;
                        location[1] = j;
                        location[4] = x;
                        break;
                    }
                }
            }
        }//finds somewhere for the first side of the domino to be placed
        if(location[0] == EMPTY) {
            return location;
        }//if location is EMPTY then there is no place for the domino to get placed so it returns an empty array that will eventually pass the turn
        for(int i = location[0] - 1; i < location[0] + 2; i++) {
            for(int j = location[1] - 1; i < location[1] + 2; j++) {
                if(dgs.isValid(i, j, side2, 2)) {
                    location[2] = i;
                    location[3] = j;
                    break;
                }
            }
        }//looks for the second side of the domino to get placed
        return location;
    }
}
