package com.example.dominoes;

import java.util.ArrayList;
import com.example.dominoes.GameFramework.players.Domino;

public class RealPlayer extends Player{

    String name;
    ArrayList<Domino> hand;
    public RealPlayer (String initName) {
        super();
        name = initName;

    }
}
