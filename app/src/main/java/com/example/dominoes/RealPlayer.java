package com.example.dominoes;

import android.view.View;

import java.util.ArrayList;
import android.view.View.OnClickListener;

public class RealPlayer extends Player implements OnClickListener{
    public RealPlayer(String name) {
        super(name);
    }

    public String getRealPlayerName() {
        return realPlayerName;
    }

    public void setRealPlayerName(String realPlayerName) {
        this.realPlayerName = realPlayerName;
    }

    private String realPlayerName;
    private ArrayList<Domino> realPlayerHand;
    private MainActivity myActivity;

    public void onClick(View button) {
        while (hasDomino()) {
            switch (button.getId()) {
                case R.id.dominos:
                    DominoPlaceAction place = new DominoPlaceAction(this);
                    game.sendAction(place);
                    button.invalidate();
                    break;
                case R.id.passButton:
                    DominoPassAction pass = new DominoPassAction(this);
                    game.sendAction(pass);
                    button.invalidate();
                    break;
            }
        }
    }

    public void setAsGui(MainActivity activity){
        myActivity = activity;
        domino.setOnClickListener(this);
        passButton.setOnClickListener(this);
    }
}
