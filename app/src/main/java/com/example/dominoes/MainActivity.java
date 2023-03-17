package com.example.dominoes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private ArrayList<Integer> player1Hand;
    private ArrayList<Integer> player2Hand;
    private ArrayList<Integer> player3Hand;
    private ArrayList<Integer>player4Hand;

    private ArrayList<Integer> board;
    private int currentPlayer;
    private int player1Score;
    private int player2Score;

    private int player3Score;
    private int player4Score;
    public class DominoeGameState {

        public DominoeGameState(){
            //Initialises the game state
            player1Hand = new ArrayList<>();
            player2Hand = new ArrayList<>();
            player3Hand = new ArrayList<>();
            player4Hand = new ArrayList<>();
            board = new ArrayList<Integer>();
            currentPlayer = 1;
            player1Score = 0;
            player2Score = 0;
            player4Score = 0;
        }

    }
    //These are the getters and setters for the game, which includes the
    //score, current player/turn, and each players hand.
    //
    //
    public ArrayList<Integer> getPLayer1Hand(){

        return player2Hand;
    }
    public void setPlayer1Hand(ArrayList<Integer> player1Hand){

        this.player1Hand = player1Hand;
    }

    public ArrayList<Integer> getPlayer2Hand(){ //Player 2 hand

        return player2Hand;
    }
    public void setPlayer2Hand(ArrayList<Integer> player2Hand){

        this.player2Hand = player2Hand;
    }


    public ArrayList<Integer> getPlayer3Hand(){ // Player 3 hand

        return player3Hand;
    }

    public void setPlayer3Hand(ArrayList<Integer> player3Hand){

        this.player3Hand = player3Hand;
    }


    public ArrayList<Integer> getPlayer4Hand(){ // Player 4 Hand

        return player4Hand;
    }

    public void setPlayer4Hand(ArrayList<Integer> player4Hand){

        this.player4Hand = player4Hand;
    }

    public ArrayList<Integer> getBoard(){

        return board;
    }

    public void setBoard(ArrayList<Integer> board){

        this.board = board;
    }

    public int getCurrentPlayer(){

        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer){

        this.currentPLayer = currentPLayer;
    }


}
}