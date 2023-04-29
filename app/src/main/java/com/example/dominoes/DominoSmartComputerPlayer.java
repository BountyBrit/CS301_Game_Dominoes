package com.example.dominoes;

import com.example.game.GameComputerPlayer;
import com.example.game.infoMsg.GameInfo;

import java.util.ArrayList;

/** DominoSmartComputerPlayer
 * SmartAI for Domino's
 *
 * This AI bases its turn off a verifiable boolean rather than a random boolean, which is slightly
 * smarter than the DumbAI. The smart AI will always try to place a domino if it can, only passing
 * its turn as the last considerable option.
 *
 * @author Brit Dannen
 * @author Alejandro Varela
 * @author Jackson Smith
 */

public class DominoSmartComputerPlayer extends GameComputerPlayer {

    //-----Instance Variables-----//
    DominoGameState dgs;
    private final int EMPTY = -1;
    private boolean hasDomino;
    //----------------------------//

    public DominoSmartComputerPlayer(String initName) {super(initName);}

    @Override
    protected void receiveInfo(GameInfo info) {
        dgs = new DominoGameState((DominoGameState)info);
        int[][] board = dgs.getBoard();
        ArrayList<Domino> hand = dgs.getPlayerHand(playerNum);

        // If it is not the player's turn, do nothing by returning
        if (dgs.getCurrentPlayer() != playerNum) {
            return;
        } //return

        // Set hasDomino to false before every turn so that the loop can
        // determine if the AI actually has a domino
        hasDomino = false;

        hasDomino = checkDominoInHand();

        // If the ai has a domino, then try to place it. Otherwise, pass the turn
        if (hasDomino) {
            sleep(2000);
            int[] stuff = aiMove(board, hand); // array that contains necessary info to place domino
            DominoPlaceAction dpa = new DominoPlaceAction(this, stuff[4], stuff[0], stuff[1], stuff[2], stuff[3]);
            this.game.sendAction(dpa);
        } else {
            sleep(2000);
            DominoPassAction dpsa = new DominoPassAction(this);
            this.game.sendAction(dpsa);
        }
    }//receiveInfo

    /** aiMove
     * The same movement that dumbAI uses except it checks the valid spot
     * @param board
     * @param hand
     * @return location
     */
    protected int[] aiMove(int[][] board, ArrayList<Domino> hand) {
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
                        // Save the current valid spot for that piece
                        location[0] = i;
                        location[1] = j;
                        location[4] = x;

                        // Compare that valid spot by checking the surrounding spots to determine
                        // if they are valid for not
                        for(int a = location[0] - 1; a < location[0] + 2; a++) {
                            for(int b = location[1] - 1; b < location[1] + 2; b++) {
                                if(dgs.isValid(a, b, side2)) {
                                    location[2] = a;
                                    location[3] = b;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        // If location is EMPTY then there is no place for the domino to get placed
        // so it returns an empty array that will eventually pass the turn
        if (location[0] == EMPTY) {
            return location;
        }

        // Returns array of with the dominoIndex, row1, col1, row2, & col2 to place a domino
        return location;
    }//aiMove

    /** checkDominoInHand
     * Loops through the ai's hand to check if it has a domino
     *
     * @return boolean
     */
    public boolean checkDominoInHand() {
        // Assign the AI's hand to easier variable to access
        ArrayList<Domino> playerHand = dgs.getPlayerHand(playerNum);

        // Loop through their hand to check if they have a domino
        for (int i = 0; i < (playerHand.size()); i++) {
            if // A valid domino is in their hand
            (playerHand.get(i).getEnd1() != -1) {
                return true;
            }
        }
        return false; // return false if they do not have a domino in their hand
    }//checkDominoInHand
}
