package com.example.dominoes;

import java.util.ArrayList;
import java.util.List;
import com.example.dominoes.GameFramework.players.Domino;

/** Player
 * This class has all of the relevant information for a player
 *
 * @author britdannen
 * @author Alejandro Varela
 * @author Jackson Smith
 *
 */
public class Player {
    private ArrayList<Domino> hand;
    private int score;

    public Player() {
        this.hand = new ArrayList<>();
        this.score = 0;
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

    public boolean hasDomino(Domino domino) {
        return hand.contains(domino);
    }


    public int getHandSize() {
        return hand.size();
    }
}

