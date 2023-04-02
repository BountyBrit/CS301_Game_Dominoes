package com.example.dominoes;

import android.view.View;

import java.util.ArrayList;

public class RealPlayer extends Player{
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

    public void onClick(View button) {

    }
}
