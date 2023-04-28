package com.example.dominoes;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.game.GameHumanPlayer;
import com.example.game.GameMainActivity;
import com.example.game.infoMsg.GameInfo;
import com.example.game.util.MessageBox;

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
     *
     * These variables will reference widgets that will be modified during play
     *
     */
    private GameMainActivity myActivity;
    private int[][] board;
    private ArrayList<Domino> hand;
    private ArrayList<Button> handButtons = new ArrayList<>();
    private Button    handButton1;
    private Button    handButton2;
    private Button    handButton3;
    private Button    handButton4;
    private Button    handButton5;
    private Button    handButton6;
    private Button    handButton7;
    private Button    passButton;
    private Button    rotButton;
    ImageButton[][] boardButtons = new ImageButton[10][10];
    private int EMPTY = -1;

    private int dominoClicked = -1;

    //Rotation varibales
    private int row = -1;
    private int col = -1;
    private int rotationNum =1;
    private int DominoRotation = col+1;
    RotateDomino rd = new RotateDomino();

    private String MenuStr = "Menu";
    private String QuitStr = "Quit Game";



    /* Main methods of the DominoHumanPlayer Class
     *
     */
    public void addHandButtons() {
        handButtons.add(handButton1);
        handButtons.add(handButton2);
        handButtons.add(handButton3);
        handButtons.add(handButton4);
        handButtons.add(handButton5);
        handButtons.add(handButton6);
        handButtons.add(handButton7);
    }//adds the buttons in the hands to the hashtable that holds them at the index of their value

    /**
     *
     * @param message
     */
    private void toast(CharSequence message) {
        Context context = myActivity.getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    /**
     *
     * @param name
     */
    public DominoHumanPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
    }

    /**
     *
     * @param info
     * 		the message
     */
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
                    switch (val) {
                        case 0:
                            boardButtons[i][j].setImageResource(R.drawable.domino0);
                            break;
                        case 1:
                            boardButtons[i][j].setImageResource(R.drawable.domino1);
                            break;
                        case 2:
                            boardButtons[i][j].setImageResource(R.drawable.domino2);
                            break;
                        case 3:
                            boardButtons[i][j].setImageResource(R.drawable.domino3);
                            break;
                        case 4:
                            boardButtons[i][j].setImageResource(R.drawable.domino4);
                            break;
                        case 5:
                            boardButtons[i][j].setImageResource(R.drawable.domino5);
                            break;
                        case 6:
                            boardButtons[i][j].setImageResource(R.drawable.domino6);
                            break;
                    }
//                    boardButtons[i][j].getBackground().setAlpha(255);
                }
            }
        }// Update board

        // Toast user to show whose turn it is
        int currPlayer = dgs.getCurrentPlayer();
        toast(allPlayerNames[currPlayer] + "'s turn");

    }//receiveInfo

    /**
     *
     * @param button
     */
    @Override
    public void onClick(View button) {
        switch (button.getId()) {
            case R.id.pass_button: // the pass button is clicked, then the turn is passed
                DominoPassAction pass = new DominoPassAction(this);
                this.game.sendAction(pass);
                toast("Turn Passed");
                button.invalidate();
                break;
            case R.id.rotate_button:
                // Checks rotationNum
                if // if rotationNum is 4(LEFT)
                (rotationNum == 4) {
                    rotationNum = 1;//reset back to 1(UP)
                } //Otherwise, keeping increasing rotationNum
                else {
                    rotationNum++;
                }

                // Sends message to user to indicate rotation of domino
                // then invalidates the button to be pressed again
                if (rotationNum == 1) {
                    toast("Domino rotated UP ");
                    button.invalidate();
                } else if (rotationNum == 2) {
                    toast("Domino rotated RIGHT");
                    button.invalidate();
                } else if (rotationNum == 3) {
                    toast("Domino rotated DOWN");
                    button.invalidate();
                } else if (rotationNum == 4) {
                    toast("Domino rotated LEFT");
                    button.invalidate();
                }
                break;
        }

    }//onClick

    public View getTopView() {return myActivity.findViewById(R.id.top_gui_layout);}

    /**
     *
     *
     * @param activity
     */
    @Override
    public void setAsGui(GameMainActivity activity) {
        // Remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.domino_layout);

        // Initialize the widget reference member variables
        this.passButton  = (Button)activity.findViewById(R.id.pass_button);
        this.rotButton   = (Button)activity.findViewById(R.id.rotate_button);
        this.handButton1 = (Button)activity.findViewById(R.id.handButton1);
        this.handButton2 = (Button)activity.findViewById(R.id.handButton2);
        this.handButton3 = (Button)activity.findViewById(R.id.handButton3);
        this.handButton4 = (Button)activity.findViewById(R.id.handButton4);
        this.handButton5 = (Button)activity.findViewById(R.id.handButton5);
        this.handButton6 = (Button)activity.findViewById(R.id.handButton6);
        this.handButton7 = (Button)activity.findViewById(R.id.handButton7);

        // Creates and adds ImageButtons to the array boardButtons,
        // then sets an OnClickListener to wait to place an action
        TableLayout tableLayout = activity.findViewById(R.id.table_layout);

        for (int i = 0; i < boardButtons.length; i++) {
            TableRow tableRow = new TableRow(getTopView().getContext());
            for (int j = 0; j < boardButtons.length; j++) {
                boardButtons[i][j] = new ImageButton(getTopView().getContext());
                boardButtons[i][j].setTag(i+"_"+j);

                tableRow.addView(boardButtons[i][j]);
                boardButtons[i][j].getBackground().setAlpha(20);

                boardButtons[i][j].getLayoutParams().width=100;
                boardButtons[i][j].getLayoutParams().height=80;


                boardButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View btn) {
                        row = getRowFromTag((String) btn.getTag());
                        col = getColFromTag((String) btn.getTag());

                        Log.d("BoardClicked", row + " " + col);

                        if (dominoClicked != -1) {
                            switch (rotationNum) {
                                case 1:
                                    // Create and send DominoPlaceAction with user rotation
                                    DominoPlaceAction placeUP = new DominoPlaceAction(DominoHumanPlayer.this, dominoClicked, row, col, rd.UP(row), col);
                                    DominoHumanPlayer.this.game.sendAction(placeUP);
                                    dominoClicked = -1;
                                    break;
                                case 2:
                                    // Create and send DominoPlaceAction with user rotation
                                    DominoPlaceAction placeRIGHT = new DominoPlaceAction(DominoHumanPlayer.this, dominoClicked, row, col, row, rd.RIGHT(col));
                                    DominoHumanPlayer.this.game.sendAction(placeRIGHT);
                                    dominoClicked = -1;
                                    break;
                                case 3:
                                    // Create and send DominoPlaceAction with user rotation
                                    DominoPlaceAction placeDOWN = new DominoPlaceAction(DominoHumanPlayer.this, dominoClicked, row, col, rd.DOWN(row), col);
                                    DominoHumanPlayer.this.game.sendAction(placeDOWN);
                                    dominoClicked = -1;
                                    break;
                                case 4:
                                    // Create and send DominoPlaceAction with user rotation
                                    DominoPlaceAction placeLEFT = new DominoPlaceAction(DominoHumanPlayer.this, dominoClicked, row, col, row, rd.LEFT(col));
                                    DominoHumanPlayer.this.game.sendAction(placeLEFT);
                                    dominoClicked = -1;
                                    break;
                            }
                        } else {
                            flash(Color.RED, 200);
                            toast("No Domino selected. Please select a Domino");
                        }
                    }
                }); //setOnClickListener
            }
            tableLayout.addView(tableRow);
        }

        //Listen for button presses
        passButton.setOnClickListener(this);
        rotButton.setOnClickListener(this);

    }//setAsGui

    /**
     *
     * @param tag
     * @return
     */
    private int getRowFromTag(String tag) {

        String[] rowAndColumn = tag.split("_");
        return Integer.parseInt(rowAndColumn[0]);
    }

    /**
     *
     * @param tag
     * @return
     */
    private int getColFromTag(String tag) {
        String[] rowAndColumn = tag.split("_");
        return Integer.parseInt(rowAndColumn[1]);
    }

    /**
     * callback method--called when we are notified that the game is over
     *
     * @param msg
     * 		the "game over" message sent by the game
     */
    @Override
    protected void gameIsOver(String msg) {
        // the default behavior is to put a pop-up for the user to see that tells
        // the game's result and offers two options: Quit or Return to Menu
		MessageBox.popUpChoice(msg, QuitStr, MenuStr, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface di, int val) {
                // if the user says that they want to quit,
                // exit the application
                System.exit(0);
            }}, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface di, int val) {
                // if the user says that they want to return to the menu,
                // "restart" the application
                myActivity.recreate();
            }}, myActivity);

    }
}


