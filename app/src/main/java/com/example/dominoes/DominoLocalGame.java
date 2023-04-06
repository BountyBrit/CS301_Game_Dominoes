package com.example.dominoes;

import com.example.game.GamePlayer;
import com.example.game.LocalGame;
import com.example.game.actionMsg.GameAction;

public class DominoLocalGame extends LocalGame {
    private int lastWinner;
    private int currentWinner;
    private DominoGameState dgs;

    /**
     * This ctor creates a new game state
     */
    public DominoLocalGame() {
        this.dgs = new DominoGameState();
    }

    public boolean isRoundOver() {
        return false;
    }

    public boolean isGameOver() {
        return false;
    }

    public void addScore(){

    }

    public boolean isValid(){
        return false;
    }

    public void alternatePlayer(){

    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

    }

    @Override
    protected boolean canMove(int playerIdx) {
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
