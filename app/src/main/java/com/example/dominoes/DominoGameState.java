package com.example.dominoes;

import android.app.GameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import com.example.dominoes.Player;

/** DominoGameState
 * This class is the GameState of the Domino Game
 *
 * @author britdannen
 * @author Alejandro Varela
 * @author Jackson Smith
 *
 */
public class DominoGameState {

    // instance variables for the game state
    private int[] scores;
    private Hashtable<Integer, ArrayList<Domino>> hands;
    private ArrayList<Integer> board;

    private ArrayList<Domino> tiles;

    private RealPlayer Human;
    private AIPlayer Computer1;
    private AIPlayer Computer2;
    private AIPlayer Computer3;

    private int currentPlayer;



    public DominoGameState() {
        //Initialises the game state

        board = new ArrayList<>();
        currentPlayer = 1;
        scores = new int[4];
        for(int i = 0; i < 4; i++) {
            scores[i] = 0;
        }

        tiles = new ArrayList<>();

        //Create all of the pieces
        for(int i = 6; i > -1; i--) {
            for(int j = i; j > -1; j--) {
                Domino interm = new Domino(i,j);
                tiles.add(interm);
            }
        }

        // Initializes hands and board to starting set
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                //Adds all possible domino pieces to the draw pile
                board.add(i * 10 + j);
            }
        }
        //Shuffle the draw pile
        Collections.shuffle(board);

        //Deal seven dominoes to each player within the Hashtable
        ArrayList<Domino> player1Hand = new ArrayList<>();
        ArrayList<Domino> player2Hand = new ArrayList<>();
        ArrayList<Domino> player3Hand = new ArrayList<>();
        ArrayList<Domino> player4Hand = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            player1Hand.add(tiles.remove(0));
            player2Hand.add(tiles.remove(0));
            player3Hand.add(tiles.remove(0));
            player4Hand.add(tiles.remove(0));
        }
        hands.put(0, player1Hand);
        hands.put(1, player2Hand);
        hands.put(2, player3Hand);
        hands.put(3, player4Hand);
    }

    public DominoGameState(DominoGameState other) {
        //copy the board
        board = other.getBoard();
        //copy the hands over
        this.hands = other.hands;
        //copy the scores over
        for(int i = 0; i < 4; i++) {
            this.scores[i] = other.scores[i];
        }

        //current player
        currentPlayer = other.currentPlayer;
    }

    public ArrayList<Domino> createHand() {
        ArrayList<Domino> currHand = new ArrayList<>();

        return currHand;
    }

    //
    //These are the getters and setters for the game, which includes the
    //score, current player/turn, and each players hand.
    //

    //Gets player 1 domino hand
    public ArrayList<Domino> getPLayer1Hand() {
        return hands.get(0);
    }

    //Sets player 2 domino hand
    public ArrayList<Domino> getPLayer2Hand() {
        return hands.get(1);
    }

    //Gets player 3 domino hand
    public ArrayList<Domino> getPLayer3Hand() {
        return hands.get(2);
    }

    //Sets player 4 domino hand
    public ArrayList<Domino> getPLayer4Hand() {
        return hands.get(3);
    }


    //Sets player 1 domino hands
    public void setPlayer1Hand(ArrayList<Domino> playerHand) {
        hands.remove(0);
        hands.put(0, playerHand);
    }

    //Sets player 2 domino hand
    public void setPlayer2Hand(ArrayList<Domino> playerHand) {
        hands.remove(1);
        hands.put(1, playerHand);
    }

    //Sets player 3 domino hand
    public void setPlayer3Hand(ArrayList<Domino> playerHand) {
        hands.remove(2);
        hands.put(2, playerHand);
    }

    //Sets player 4 domino hand
    public void setPlayer4Hand(ArrayList<Domino> playerHand) {
        hands.remove(3);
        hands.put(3, playerHand);
    }

    //Gets the board
    public ArrayList<Integer> getBoard() {

        return board;
    }

    //Initialises the board
    public void setBoard(ArrayList<Integer> board) {

        this.board = board;
    }

    //Calls the current/players turn
    public int getCurrentPlayer() { return currentPlayer; }

    //Gets player 1 score
    public int getPlayer1Score() {
        return scores[0];
    }

    //Sets player 1 score
    public void setPlayer1Score(int playerScore) {
        scores[0] = playerScore;
    }

    //Gets player 2 score
    public int getPlayer2Score() {
        return scores[1];
    }

    //Sets the player 2 score
    public void setPlayer2Score(int playerScore) {
        scores[1] = playerScore;
    }

    //Gets player 3 score
    public int getPlayer3Score() {
        return scores[2];
    }

    //Sets the player 3 score
    public void setPlayer3Score(int playerScore) {
        scores[2] = playerScore;
    }

    //Gets player 4 score
    public int getPlayer4Score() {
        return scores[3];
    }

    //Sets the player 4 score
    public void setPlayer4Score(int playerScore) {
        scores[3] = playerScore;
    }



    public boolean placeDomino(Domino domino, Player player) {
        //cannot place because the arraylists are neither initialized nor Domino objects
        return false;
    }

    public boolean drawDomino(Player player){
        //cannot place because the arraylists are neither initialized nor Domino objects
        return false;
    }

}
