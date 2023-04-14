package com.example.dominoes;

import android.view.View;
import android.widget.Button;

import com.example.game.GamePlayer;
import com.example.game.LocalGame;
import com.example.game.actionMsg.GameAction;

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
    private DominoGameState dgs;
    private final int EMPTY = -1;


    /**
     * This ctor creates a new game state
     */
    public DominoLocalGame() {
        this.dgs = new DominoGameState();
    }

    public void alternatePlayer() {
        dgs.rotatePlayer();
    }//next player's turn

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        DominoGameState copyDGS = new DominoGameState(dgs);
        p.sendInfo(copyDGS);
    }//sends the new gamestate to the gamestate object

    @Override
    protected boolean canMove(int playerIdx) {
        if (playerIdx == dgs.getCurrentPlayer()) {
            return true;
        } else {
            return false;
        }
//        return true;
    }//tells a player if it is they're turn

    @Override
    protected String checkIfGameOver() {
//        if (dgs.getPlayerHand(dgs.getCurrentPlayer) == null){
//            return "Congrats! You win";
//        }
        return null;
    }//returns of the game has been won

    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof DominoPassAction) {// Action is passing turn
            alternatePlayer();
            return true;
        } // Action is placing a domino
        else if (action instanceof DominoPlaceAction) {
            // Temporary Variables from DominoHumanPlayer
            int DominoIndex = ((DominoPlaceAction) action).getDominoClicked();
            int ROW_1 = ((DominoPlaceAction) action).getRow_1();
            int COL_1 = ((DominoPlaceAction) action).getCol_1();
            int ROW_2 = ((DominoPlaceAction) action).getRow_2();
            int COL_2 = ((DominoPlaceAction) action).getCol_2();

            // Placing the domino on the board in the current GameState
            dgs.placeDomino(dgs.getCurrentPlayer(), dgs.getPlayerHand(dgs.getCurrentPlayer()).get(DominoIndex), ROW_1, COL_1, ROW_2, COL_2);
            alternatePlayer();// Alternate to the next player
            return true;
        }
        return false;
    }//takes in action from player and calls place domino in DominoGameState


    public boolean passTurn() {
        alternatePlayer();
        return true;
    }//passes the current player's turn


}
