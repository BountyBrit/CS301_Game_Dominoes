package com.example.dominoes;

import android.view.View;
import android.widget.Button;

import com.example.game.GamePlayer;
import com.example.game.LocalGame;
import com.example.game.actionMsg.GameAction;

import java.util.ArrayList;

/**Domino Local Game
 *
 *Class DominoLocalGame controls the play of the game
 *
 * @author Alejandro Varela Iturralde
 * @author Brit Dannen
 * @author Jackson Smith
 *
 */
public class DominoLocalGame extends LocalGame {
    // Instance Variables
    private DominoGameState dgs;
    private final int EMPTY = -1;


    /** DominoLocalGame
     * This ctor creates a new game state
     */
    public DominoLocalGame() {
        this.dgs = new DominoGameState();
    }//DominoLocalGame

    /** alternatePlayer
     * alternates the player using the rotatePlayer method in DominoGameState
     *
     */
    public void alternatePlayer() {
        dgs.rotatePlayer();
    }//alternatePlayer

    /** sendUpdatedStateTo
     * sends the new gamestate to the gamestate object
     *
     * @param p
     * 			the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        DominoGameState copyDGS = new DominoGameState(dgs);
        p.sendInfo(copyDGS);
    }//sendUpdatedStateTo

    /** canMove
     *
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if (playerIdx == dgs.getCurrentPlayer()) {
            return true;
        } else {
            return false;
        }
//        return true;
    }//canMove

    /** checkIfGameOver
     *
     * @return
     */
    @Override
    protected String checkIfGameOver(){
        for (int i = 0; i < (dgs.getHand().size()); i++){
            ArrayList<Domino> playerHand = dgs.getPlayerHand(i);
            int count = 0;
            for (int j = 0; j < (playerHand.size()); j++) {
                if (playerHand.get(j).getEnd1() == -1) {
                    count++;
                }
                if (count == 7) {
                    return this.playerNames[i] + " wins!";
                }
            }
        }
        return null;
    }//checkIfGameOver

    /** makeMove
     *
     * @param action
     * 			The move that the player has sent to the game
     * @return
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if // Action is passing turn
        (action instanceof DominoPassAction) {
            alternatePlayer();
            return true;
        } else if // Action is placing a domino
        (action instanceof DominoPlaceAction) {
            // Temporary Variables from DominoHumanPlayer
            int DominoIndex = ((DominoPlaceAction) action).getDominoClicked();
            int ROW_1 = ((DominoPlaceAction) action).getRow_1();
            int COL_1 = ((DominoPlaceAction) action).getCol_1();
            int ROW_2 = ((DominoPlaceAction) action).getRow_2();
            int COL_2 = ((DominoPlaceAction) action).getCol_2();

            // Placing the domino on the board in the current GameState
            dgs.placeDomino(dgs.getCurrentPlayer(),dgs.getPlayerHand(dgs.getCurrentPlayer()).get(DominoIndex), DominoIndex, ROW_1, COL_1, ROW_2, COL_2);
            alternatePlayer();// Alternate to the next player
            return true;
        }
        return false;
    }//makeMove

}
