package com.example.dominoes;

public class DominoLocalGame {
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
}
