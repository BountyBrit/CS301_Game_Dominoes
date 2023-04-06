package com.example.dominoes;

import android.view.View;
import android.widget.ImageView;

import com.example.game.GameMainActivity;
import com.example.game.LocalGame;
import com.example.game.config.GameConfig;

import java.util.ArrayList;

/** MainActivity
 *
 * @author britdannen
 * @author Alejandro Varela
 * @author Jackson Smith
 *
 * dummy to commit/push
 */
public class DominoMainActivity extends GameMainActivity {
    private Domino[][] dominoBoard;
    private int currentPlayer;
    ArrayList<Domino> currentPLayerHand;
    private boolean isGameOver = false;
    private ImageView dominoDeck;
    private ImageView playArea;
    private DominoComputerPlayer aiPlayer;


    @Override
    public GameConfig createDefaultConfig() {
        return null;
    }

    @Override
    public LocalGame createLocalGame() {
        return null;
    }

    public void onClick(View view) {
        DominoGameState firstInstance = new DominoGameState();
        DominoGameState secondInstance = new DominoGameState(firstInstance);

        Domino tile = new Domino(1,2);
        String name = null;
        DominoHumanPlayer player = new DominoHumanPlayer(null);
        firstInstance.placeDomino(tile, player);
        firstInstance.drawDomino(player);

        ArrayList<Integer> board;
        board = firstInstance.getBoard();
        //board.add(tile);
    }

    public void addPlayer() {
        // null for now
    }
    public void removePlayer() {
        // null for now
    }

}
