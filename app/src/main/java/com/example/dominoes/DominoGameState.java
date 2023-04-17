package com.example.dominoes;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import com.example.game.GameMainActivity;
import com.example.game.GamePlayer;
import com.example.game.LocalGame;
import com.example.game.actionMsg.GameAction;
import com.example.game.config.GameConfig;
import com.example.game.infoMsg.GameState;

/** DominoGameState
 * This class is the GameState of the Domino Game
 *
 * @author britdannen
 * @author Alejandro Varela
 * @author Jackson Smith
 *
 */
public class DominoGameState extends GameState {

    // instance variables for the game state
    private int[] scores;
    private Hashtable<Integer, ArrayList<Domino>> hands =  new Hashtable<>();
    private int[][] board;

    private ArrayList<Domino> tiles;

//    private DominoComputerPlayer Computer1;
//    private DominoComputerPlayer Computer2;
//    private DominoComputerPlayer Computer3;
    private int currentPlayer;
    private int EMPTY = -1; //Designates empty space as -1


    public DominoGameState() {
        // Initializes board
        board = new int[10][10];

        //Fills the board with empty spaces
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++){
                board[row][col] = EMPTY;
            }
        }

        currentPlayer = 0;//sets current player to human
        scores = new int[4];
        for(int i = 0; i < 4; i++) {
            scores[i] = 0;
        }

        // Initalizes tiles to be added to hands
        tiles = new ArrayList<>();

        //Create all of the pieces
        for(int i = 6; i > -1; i--) {
            for(int j = i; j > -1; j--) {
                Domino interm = new Domino(i,j);
                tiles.add(interm);
            }
        }

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
        // Assign player hands to hands list
        hands.put(0, player1Hand);
        hands.put(1, player2Hand);
        hands.put(2, player3Hand);
        hands.put(3, player4Hand);

    } //DominoGameState cntr

    public DominoGameState(DominoGameState other) {
        //copy the board
        board = other.getBoard();
        //copy the hands over
        this.hands = other.hands;
        //copy the scores over
        this.scores = new int[4];
//        for(int i = 0; i < 4; i++) {
//            this.scores[i] = other.scores[i];
//        }

        //current player
        currentPlayer = other.currentPlayer;
    }

    public ArrayList<Domino> createHand() {
        ArrayList<Domino> currHand = new ArrayList<>();
        return currHand;
    }
    public boolean rotatePlayer() {
        if(currentPlayer == 3) {
            currentPlayer = 0;
            return true;
        }
        currentPlayer += 1;
        return false;
    }

    /*
    * These are the getters and setters for the game, which includes the
    * score, current player/turn, board, and each players hand.
    */
    public ArrayList<Domino> getPlayerHand(int playerIndx) {
        return hands.get(playerIndx);
    }

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

    public void setPlayerHand(ArrayList<Domino> playerHand, int playerIndx) {
        hands.remove(playerIndx);
        hands.put(playerIndx, playerHand);
    }

    //Gets the board
    public int[][] getBoard() {
        return this.board;
    }

    //Initializes the board
    public void setBoard(int[][] board) {
        this.board = board;
    }

    // Get the value at a specific index on the board
    public int getBoardSlot(int row, int col) {
        return board[row][col];
    }

    // Set the value at a specific index on the board
    public void setBoardSlot(int row, int col, int value) {
        board[row][col] = value;
    }

    // Get the size of the board
    public int getBoardSize() {
        return board.length;
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

    public void removeDominoFromHand(int playerIndx, Domino domino) {
        getPlayerHand(playerIndx).remove(domino);
    }

    public void placeDomino(int playerIndex, Domino domino, int domino_clicked, int row1, int col1, int row2, int col2) {
        // Check if the player has the domino in their hand
//        if (getPlayerHand(playerIndex).get(domino_clicked) == domino) {
//            // Check if the board slot is empty
//            if ( (getBoardSlot(row1, col1) == EMPTY) && (getBoardSlot(row2, col2) == EMPTY) ) {
                // Add the domino to the board and remove it from the player's hand
                //isValid
                setBoardSlot(row1, col1, domino.getEnd1());
                setBoardSlot(row2, col2, domino.getEnd2());
                removeDominoFromHand(playerIndex, domino);
//                }
//        }
    }//placeDomino

    public boolean isValid(int row, int col, int val, int side) {
        int empty = 0;
        for(int i = 0; i < 10; i++) {
            for( int j = 0; j < 10; j++) {
                empty++;
            }
        }
        if(empty == 100) {return true;}
        //for the first turn always return true

        int emptyTotal = 0;
        int total = 0;
        for(int i = row - 1; i < row + 2; i++) {
            for(int j = col - 1; j < col + 2; j++) {
                if(i > 9 || j > 9 || i < 0 || j < 0) {continue;}
                if(board[i-1][j-1] != EMPTY || board[i+1][j-1] != EMPTY || board[i-1][j+1] != EMPTY || board[i+1][j+1] != EMPTY) {return false;}
                total++;
                if(board[i][j] == EMPTY) {emptyTotal++;}
                if(board[i][j] != val && board[i][j] != EMPTY ){return false;}
            }
        }
        if(side == 1) {
            return emptyTotal == total - 1;
        }
        return emptyTotal >= total - 2;
    }


}
