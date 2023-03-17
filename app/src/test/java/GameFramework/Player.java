package GameFramework;

import java.util.ArrayList;
import java.util.List;

/** Player
 * This class has all of the relevant information for a player
 *
 * @author britdannen
 * @author Alejandro Varela
 * @author Jackson Smith
 *
 */
public class Player {
    private String name;
    private List<Domino> hand;
    private int score;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Domino>();
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public List<Domino> getHand() {
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

    public boolean hasDominoes() {
        return !hand.isEmpty();
    }

    public int getHandSize() {
        return hand.size();
    }
}

