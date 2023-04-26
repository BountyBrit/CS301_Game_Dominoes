package com.example.dominoes;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.game.GameHumanPlayer;
import com.example.game.GameMainActivity;
import com.example.game.infoMsg.GameInfo;

import java.util.ArrayList;


/** Player
 * This class has all of the relevant information for a player
 *
 * @author britdannen
 * @author Alejandro Varela
 * @author Jackson Smith
 *
 */
public class DominoHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    /* Instance Variables
     * These variables will reference widgets that will be modified during play */
    //private player player_1;
    private Button    handButton1;
    private Button    handButton2;
    private Button    handButton3;
    private Button    handButton4;
    private Button    handButton5;
    private Button    handButton6;
    private Button    handButton7;
    private Button    passButton;

    private GameMainActivity myActivity;

    private int[][] board;
    private ArrayList<Domino> hand;
    private ArrayList<Button> handButtons = new ArrayList<>();

    Button[][] boardButtons = new Button[10][10];

    private boolean boardButtonClicked = false;

    private boolean turn = true;

    private int row = -1;
    private int col = -1;

    private int EMPTY = -1;

    private int dominoClicked = -1;

    public void addHandButtons() {
        handButtons.add(handButton1);
        handButtons.add(handButton2);
        handButtons.add(handButton3);
        handButtons.add(handButton4);
        handButtons.add(handButton5);
        handButtons.add(handButton6);
        handButtons.add(handButton7);
    }//adds the buttons in the hands to the hashtable that holds them at the index of their value


    private void toast(CharSequence message) {
        Context context = myActivity.getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public DominoHumanPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
    }

    @Override
    public void receiveInfo(GameInfo info) {

        // Checks to ensure info object is a DominoGameState object
        if (!(info instanceof DominoGameState)) {
            flash(Color.RED, 200);
            return;
        }

        // Set handButton text to dominos in player's hand
        DominoGameState dgs = new DominoGameState((DominoGameState) info);
        hand = dgs.getPlayerHand(playerNum);
        board = dgs.getBoard();

        addHandButtons();// Add hand buttons to handButtons Array

        // Set the text of the handbuttons to the end values of their domino
        handButton1.setText(hand.get(0).toString());
        handButton2.setText(hand.get(1).toString());
        handButton3.setText(hand.get(2).toString());
        handButton4.setText(hand.get(3).toString());
        handButton5.setText(hand.get(4).toString());
        handButton6.setText(hand.get(5).toString());
        handButton7.setText(hand.get(6).toString());

        // Create and sets onClickListeners for hand buttons
        for (int i = 0; i < hand.size(); i++){
            Button dominoButton = handButtons.get(i);
            Domino domino = hand.get(i);

            dominoButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedIndex = hand.indexOf(domino);
                    dominoClicked = clickedIndex;
                    Log.d("DominoClicked", "Index of Domino: "+dominoClicked);
                }
            });
        }//setOnClickListeners

        // Update the board
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != EMPTY) {
                    int val = board[i][j];
                    boardButtons[i][j].setText(""+val);
                }
            }
        }// Update board

        int currPlayer = dgs.getCurrentPlayer();

        if (currPlayer == 0) {
            toast("Your turn");
        } else if (currPlayer == 1) {
            toast("Player 2's turn");
        } else if (currPlayer == 2) {
            toast("Player 3's turn");
        } else if (currPlayer == 3) {
            toast("Player 4's turn");
        }


    }

    public ArrayList<Domino> getHand() {
        return hand;
    }//returns the hand of the current player

    public void removeDominoFromHand(Domino domino) {
        hand.remove(domino);
    }//removes a domino from the current players hand

    public boolean hasDomino() {
        Domino domino = null;
        return hand.contains(null);
    }//returns whether this player has dominos

    public int getHandSize() {
        return hand.size();
    }//returns the size of this player's hand

    @Override
    public void onClick(View button) {
        while (turn) {
            if (button.getId() == R.id.pass_button) {
                DominoPassAction pass = new DominoPassAction(this);
                this.game.sendAction(pass);
                button.invalidate();
                turn = false;
                toast("Turn Passed");
            }//if the button clicked passes then the turn is passed
        }
    }

    public View getTopView() {return myActivity.findViewById(R.id.top_gui_layout);}

    @Override
    public void setAsGui(GameMainActivity activity) {
        // Remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.domino_layout);

        // Initialize the widget reference member variables
        this.passButton  = (Button)activity.findViewById(R.id.pass_button);
        this.handButton1 = (Button)activity.findViewById(R.id.handButton1);
        this.handButton2 = (Button)activity.findViewById(R.id.handButton2);
        this.handButton3 = (Button)activity.findViewById(R.id.handButton3);
        this.handButton4 = (Button)activity.findViewById(R.id.handButton4);
        this.handButton5 = (Button)activity.findViewById(R.id.handButton5);
        this.handButton6 = (Button)activity.findViewById(R.id.handButton6);
        this.handButton7 = (Button)activity.findViewById(R.id.handButton7);

        // Creates and adds buttons to the array boardButtons,
        // then
        TableLayout tableLayout = activity.findViewById(R.id.table_layout);

        for (int i = 0; i < boardButtons.length; i++) {
            TableRow tableRow = new TableRow(getTopView().getContext());
            for (int j = 0; j < boardButtons.length; j++) {
                boardButtons[i][j] = new Button(getTopView().getContext());
                boardButtons[i][j].setTag(i+"_"+j) ;

                tableRow.addView(boardButtons[i][j]);

                boardButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View btn) {
                        row = getRowFromTag((String)btn.getTag());
                        col = getColFromTag((String) btn.getTag());

                        Log.d("BoardClicked", row + " " + col);

                        if (dominoClicked != -1) {
                            DominoPlaceAction place = new DominoPlaceAction(DominoHumanPlayer.this, dominoClicked, row, col, row, (col + 1));
                            DominoHumanPlayer.this.game.sendAction(place);
                            toast("Domino Placed");
                        } else {
                            flash(Color.RED, 200);
                            toast("No Domino Clicked. Please select a Domino");
                        }
                    }
                }); //setOnClickListener
            }
            tableLayout.addView(tableRow);
        }

        //Listen for button presses
        passButton.setOnClickListener(this);
    }//setAsGui

    private int getRowFromTag(String tag) {

        String[] rowAndColumn = tag.split("_");
        return Integer.parseInt(rowAndColumn[0]);
    }
    private int getColFromTag(String tag) {
        String[] rowAndColumn = tag.split("_");
        return Integer.parseInt(rowAndColumn[1]);
    }
}


