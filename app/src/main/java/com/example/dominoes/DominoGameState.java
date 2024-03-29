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
    private Hashtable<Integer, ArrayList<Domino>> hands =  new Hashtable<>();
    private int[][] board;
    private ArrayList<Domino> tiles;
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
        // Sets current player to human
        currentPlayer = 0;

        // Initalizes tiles to be added to hands
        tiles = new ArrayList<>();

        //Create all of the pieces
        for(int i = 6; i > -1; i--) {
            for(int j = i; j > -1; j--) {
                Domino interm = new Domino(i,j);
                tiles.add(interm);
            }
        }

        // Deal seven dominoes to each player within the Hashtable
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

        //current player
        currentPlayer = other.currentPlayer;
    }

    public ArrayList<Domino> createHand() {
        ArrayList<Domino> currHand = new ArrayList<>();
        return currHand;
    }

    /**rotatePlayer
     *
     * @return
     */
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

    public Hashtable<Integer, ArrayList<Domino>> getHand() {return hands;}

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

    /**removeDominoFromHand
     *
     * @param playerIndx
     * @param domino_clicked
     */
    public void removeDominoFromHand(int playerIndx, int domino_clicked) {
        // Sets the end values to -1
        // toString in Domino will return empty string if end values are -1
        getPlayerHand(playerIndx).get(domino_clicked).setEnd1(-1);
        getPlayerHand(playerIndx).get(domino_clicked).setEnd2(-1);
    }

    /**placeDomino
     *
     * @param playerIndex
     * @param domino
     * @param domino_clicked
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     */
    public void placeDomino(int playerIndex, Domino domino, int domino_clicked, int row1, int col1, int row2, int col2) {
        setBoardSlot(row1, col1, domino.getEnd1());
        setBoardSlot(row2, col2, domino.getEnd2());
        removeDominoFromHand(playerIndex, domino_clicked);
    }//placeDomino

    public boolean isValid(int row, int col, int val) {
        int empty = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == EMPTY) {
                    empty++;
                }
            }
        }
        if (empty == 100) {
            return true;
        }
        //for the first turn always return true
        int side = 2;
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if(!inBounds(i, j)){continue;}
                if(i == row && j == col) {
                    continue;
                }
                if(board[i][j] != EMPTY) {
                    side = 1;
                    break;
                }
            }
        }//finds the side of the domino currently on

        int connected = connect(row,col,val);
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if(!inBounds(i, j)){continue;}
                if(side == 2) {
                    if(board[i][j] != EMPTY) {
                        return false;
                    }
                }
                else if(side == 1) {
                    int total = 0;
                    if(connected == 0) {return false;}
                    else if(connected == 1) {
                        if(board[i + 1][j - 1] != EMPTY || board[i + 1][j + 1] != EMPTY) {
                            return false;
                        }
                    }
                    else if(connected == 2) {
                        if(board[i + 1][j - 1] != EMPTY || board[i - 1][j - 1] != EMPTY) {
                            return false;
                        }
                    }
                    else if(connected == 3) {
                        if(board[i - 1][j - 1] != EMPTY || board[i - 1][j + 1] != EMPTY) {
                            return false;
                        }
                    }
                    else if(connected == 4) {
                        if(board[i + 1][j + 1] != EMPTY || board[i - 1][j + 1] != EMPTY) {
                            return false;
                        }
                    }
                    else if(i == row - 1 || i == row + 1 || j == col - 1 || j == col + 1) {
                        if(board[row][col] == val) {
                            if(total > 1){return false;}
                            total++;
                        }
                        else if(board[row][col] != val) {return false;}
                    }
                }
            }
        }
        return true;
    }

    private int connect(int row, int col, int val) {
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if(inBounds(i-1, j)){
                    if(board[i-1][j] == val) {return 1;}
                }
                if(inBounds(i+1, j)){
                    if(board[i+1][j] == val) {return 1;}
                }
                if(inBounds(i, j+1)){
                    if(board[i][j+1] == val) {return 1;}
                }
                if(inBounds(i, j-1)){
                    if(board[i][j-1] == val) {return 1;}
                }
            }
        }
        return 0;
    }

    private boolean inBounds(int row,int col) {
        if(row > -1 && col > -1 && row < 10 && col < 10) {
            return true;
        }
        return false;
    }
}
