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
 * @Author Alejandro Varela Iturralde
 * @Author Brit Dannen
 * @Author Jackson Smith
 *
 */
public class DominoLocalGame extends LocalGame {
//    private int lastWinner;
//    private int currentWinner;
    private DominoGameState dgs;
    private final int EMPTY = -1;


    /**
     * This ctor creates a new game state
     */
    public DominoLocalGame() {
        this.dgs = new DominoGameState();
    }

    public void addScore() {
    }

    public void alternatePlayer() {
        dgs.rotatePlayer();
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        DominoGameState copyDGS = new DominoGameState(dgs);
        p.sendInfo(copyDGS);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        if (playerIdx == dgs.getCurrentPlayer()) {
            return true;
        } else {
            return false;
        }
//        return true;
    }

    @Override
    protected String checkIfGameOver() {
//        if (dgs.getPlayerHand(dgs.getCurrentPlayer) == null){
//            return "Congrats! You win";
//        }
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof DominoPassAction) {
            passTurn();
            return true;
        } //else if (action instanceof DominoPlaceAction) {
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }


    public boolean passTurn() {
        alternatePlayer();
        return true;
    }


}
