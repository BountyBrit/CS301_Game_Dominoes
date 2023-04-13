package com.example.dominoes;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    /* instance variables */

    // These variables will reference widgets that will be modified during play
    private Button    handButton1 = null;
    private Button    handButton2 = null;
    private Button    handButton3 = null;
    private Button    handButton4 = null;
    private Button    handButton5 = null;
    private Button    handButton6 = null;
    private Button    handButton7 = null;
    private Button    passButton  = null;

    private GameMainActivity myActivity;

    private int[][] board;
    private ArrayList<Domino> hand;

    private int score;

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
        hand = dgs.getPlayerHand(0);
        board = dgs.getBoard();
        handButton1.setText(hand.get(0).toString());
        handButton2.setText(hand.get(1).toString());
        handButton3.setText(hand.get(2).toString());
        handButton4.setText(hand.get(3).toString());
        handButton5.setText(hand.get(4).toString());
        handButton6.setText(hand.get(5).toString());
        handButton7.setText(hand.get(6).toString());

        // Update the board and player's hand
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
//            case R.id.handButton1:
//                switch (button.getId()) {
//                    case
//                        break;
//                }
//                break;
//
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
        this.passButton          = (Button)activity.findViewById(R.id.pass_button);

        this.handButton1 = (Button)activity.findViewById(R.id.handButton1);
        this.handButton2 = (Button)activity.findViewById(R.id.handButton2);
        this.handButton3 = (Button)activity.findViewById(R.id.handButton3);
        this.handButton4 = (Button)activity.findViewById(R.id.handButton4);
        this.handButton5 = (Button)activity.findViewById(R.id.handButton5);
        this.handButton6 = (Button)activity.findViewById(R.id.handButton6);
        this.handButton7 = (Button)activity.findViewById(R.id.handButton7);


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


