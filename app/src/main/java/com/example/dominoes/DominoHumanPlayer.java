package com.example.dominoes;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.game.GameHumanPlayer;
import com.example.game.GameMainActivity;
import com.example.game.infoMsg.GameInfo;

import java.util.ArrayList;

//dummy comment


/** Player
 * This class has all of the relevant information for a player
 *
 * @author britdannen
 * @author Alejandro Varela
 * @author Jackson Smith
 *
 */
public class DominoHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    /* instance variables */

    // These variables will reference widgets that will be modified during play
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


    public void addHandButtons() {
        handButtons.add(handButton1);
        handButtons.add(handButton2);
        handButtons.add(handButton3);
        handButtons.add(handButton4);
        handButtons.add(handButton5);
        handButtons.add(handButton6);
        handButtons.add(handButton7);
    }
    private int score;

    private int dominoClicked;

    public DominoHumanPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
        this.score = 0;
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
        boardButtons = new Button[10][10];

        addHandButtons();

        handButton1.setText(hand.get(0).toString());
        handButton2.setText(hand.get(1).toString());
        handButton3.setText(hand.get(2).toString());
        handButton4.setText(hand.get(3).toString());
        handButton5.setText(hand.get(4).toString());
        handButton6.setText(hand.get(5).toString());
        handButton7.setText(hand.get(6).toString());

        for (int i = 0; i < hand.size(); i++){
            Button dominoButton = handButtons.get(i);
            Domino domino = hand.get(i);

            dominoButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedIndex = hand.indexOf(domino);
                    dominoClicked = clickedIndex;
                    Log.i("DominoClicked", toStringDomino());
                }
            });
        }

        // Update the board and player's hand
    }

    public String toStringDomino(){
        return ""+dominoClicked;
    }
    public ArrayList<Domino> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addDominoToHand(Domino domino) {
        hand.add(domino);
    }

    public void removeDominoFromHand(Domino domino) {
        hand.remove(domino);
    }

    public boolean hasDomino() {
        Domino domino = null;
        return hand.contains(null);
    }

    public int getHandSize() {
        return hand.size();
    }

    @Override
    public void onClick(View button) {
        switch (button.getId()) {
            case R.id.pass_button:
                DominoPassAction pass = new DominoPassAction(this);
                this.game.sendAction(pass);
                button.invalidate();
                break;
        }
    }

    public View getTopView() {return myActivity.findViewById(R.id.top_gui_layout);}

    @Override
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.domino_layout);

        //Initialize the widget reference member variables
        this.passButton  = (Button)activity.findViewById(R.id.pass_button);

        this.handButton1 = (Button)activity.findViewById(R.id.handButton1);
        this.handButton2 = (Button)activity.findViewById(R.id.handButton2);
        this.handButton3 = (Button)activity.findViewById(R.id.handButton3);
        this.handButton4 = (Button)activity.findViewById(R.id.handButton4);
        this.handButton5 = (Button)activity.findViewById(R.id.handButton5);
        this.handButton6 = (Button)activity.findViewById(R.id.handButton6);
        this.handButton7 = (Button)activity.findViewById(R.id.handButton7);

        for (int i = 0; i < boardButtons.length; i++) {
            for (int j = 0; j < boardButtons.length; j++) {
                boardButtons[i][j] = new Button(getTopView().getContext());
                boardButtons[i][j].setTag("Button" + i + "_" + j) ;

                boardButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View btn) {
                        boardButtonClicked = true;

                        Log.i("BoardClicked", "fuck");
                    }
                });
            }
        }
        TableLayout tableLayout = activity.findViewById(R.id.table_layout);
        for (int i = 0; i < boardButtons.length; i++) {
            TableRow tableRow = new TableRow(getTopView().getContext());
            for (int j = 0; j < boardButtons.length; j++) {
                tableRow.addView(boardButtons[i][j]);
            }
            tableLayout.addView(tableRow);
        }



        //Listen for button presses
        passButton.setOnClickListener(this);

        handButton1.setOnClickListener(this);
        handButton2.setOnClickListener(this);
        handButton3.setOnClickListener(this);
        handButton4.setOnClickListener(this);
        handButton5.setOnClickListener(this);
        handButton6.setOnClickListener(this);
        handButton7.setOnClickListener(this);
    }

}


