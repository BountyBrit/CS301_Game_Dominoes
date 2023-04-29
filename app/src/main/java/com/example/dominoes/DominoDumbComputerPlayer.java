package com.example.dominoes;

import com.example.game.GameComputerPlayer;
import com.example.game.infoMsg.GameInfo;

import java.util.ArrayList;
import java.util.Random;

/** DominoDumbComputerPlayer
 * DumbAI for Domino's. This AI will randomly choose between passing and placing a turn
 *
 * @author Alejandro Varela
 * @author Brit Dannen
 * @author Jackson Smith
 *
 */
public class DominoDumbComputerPlayer extends GameComputerPlayer {
    DominoGameState dgs;
    private final int EMPTY = -1;
    public DominoDumbComputerPlayer(String initName) {super(initName);}

    @Override
    protected void receiveInfo(GameInfo info) {
        dgs = new DominoGameState((DominoGameState)info);
        int[][] board = dgs.getBoard();
        ArrayList<Domino> hand = dgs.getPlayerHand(playerNum);

        // If it is not the player's turn, do nothing by returning
        if (dgs.getCurrentPlayer() != playerNum) {
            return;
        } //return

        // Creates a random object that will determine DumbAI's move
        Random rnd = new Random();

        // If-statement which will randomly choose to Pass or Place a domino
        if (rnd.nextBoolean()) {
            sleep(5000);
            int[] stuff = aiMove(board, hand);
            DominoPlaceAction dpa = new DominoPlaceAction(this, stuff[4], stuff[0], stuff[1], stuff[2], stuff[3]);
            this.game.sendAction(dpa);
        } else {
            sleep(3000);
            DominoPassAction dpsa = new DominoPassAction(this);
            this.game.sendAction(dpsa);
        }
    }

    /** aiMove
     *
     * @param board
     * @param hand
     * @return location
     */
    protected int[] aiMove(int[][] board, ArrayList<Domino> hand) {
        // Sides of a domino that could be placed
        int side1 = EMPTY;
        int side2 = EMPTY;

        // Create a new array to store dominoIndex, row1, col1, row2, & col2
        int[] location = new int[5];

        location[0] = EMPTY;
        location[1] = EMPTY;

        // Finds somewhere on the board for the first side of the domino to be placed
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                for(int x = 0; x < hand.size();x++) {
                    side1 = hand.get(x).getEnd1();
                    side2 = hand.get(x).getEnd2();
                    if(dgs.isValid(i,j,side1)) {
                        location[0] = i;
                        location[1] = j;
                        location[4] = x;
                        break;
                    }
                }
            }
        }

        // If location is EMPTY then there is no place for the domino to get placed
        // so it returns an empty array that will eventually pass the turn
        if(location[0] == EMPTY) {
            return location;
        }

        // Looks for the second side of the domino to get placed
        for(int i = location[0] - 1; i < location[0] + 2; i++) {
            for(int j = location[1] - 1; i < location[1] + 2; j++) {
                if(dgs.isValid(i, j, side2)) {
                    location[2] = i;
                    location[3] = j;
                    break;
                }
            }
        }

        // Returns array of with the dominoIndex, row1, col1, row2, & col2
        return location;
    }//aiMove
}
