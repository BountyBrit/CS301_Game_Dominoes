package com.example.dominoes;

import android.view.View;

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


    public void addScore(){}

    public boolean isValid(){
        return false;
    }

    public void alternatePlayer(){}

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
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }

    public boolean placeDomino(Domino domino, DominoHumanPlayer player, View view) {
        if(player.hasDomino()){return false;}
        else {
            player.onClick(view);
        }
        return false;
    }

    public boolean passTurn(DominoHumanPlayer player){
        return false;
    }
}
