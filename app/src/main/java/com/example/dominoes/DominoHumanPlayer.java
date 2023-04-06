package com.example.dominoes;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    private TextView    playerScoreTextView = null;
    private TextView    oppScoreTextView    = null;
    private TextView    messageTextView     = null;
    private Button      drawButton          = null;
    private Button      passButton          = null;
    private GameMainActivity myActivity;

    private ArrayList<Domino> hand;
    private int score;

    public DominoHumanPlayer(String name) {
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

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
    public void onClick(View view) {
//        while (hasDomino()) {
//            switch (button.getId()) {
//                case R.id.dominos:
//                    DominoPlaceAction place = new DominoPlaceAction(this);
//                    game.sendAction(place);
//                    button.invalidate();
//                    break;
//                case R.id.passButton:
//                    DominoPassAction pass = new DominoPassAction(this);
//                    game.sendAction(pass);
//                    button.invalidate();
//                    break;
//            }
//        }
    }
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }
    //dummy comment
    @Override
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.domino_layout);

        //Initialize the widget reference member variables
        //this.playerScoreTextView = (TextView)activity.findViewById(R.id.yourScoreValue);
        //this.oppScoreTextView    = (TextView)activity.findViewById(R.id.oppScoreValue);
        //this.messageTextView     = (TextView)activity.findViewById(R.id.messageTextView);
        this.drawButton          = (Button)activity.findViewById(R.id.draw_button);
        this.passButton          = (Button)activity.findViewById(R.id.pass_button);

        //Listen for button presses
        drawButton.setOnClickListener(this);
        passButton.setOnClickListener(this);

    }
}


