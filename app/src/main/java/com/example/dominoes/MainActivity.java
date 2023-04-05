package com.example.dominoes;

import androidx.appcompat.app.AppCompatActivity;
import com.example.dominoes.GameFramework.players.Domino;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

/** MainActivity
 *
 * @author britdannen
 * @author Alejandro Varela
 * @author Jackson Smith
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Domino[][] DominoBoard;
    private int currentPlayer;
    ArrayList<Domino> currentPLayerHand;
    private boolean isGameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        DominoGameState firstInstance = new DominoGameState();
        DominoGameState secondInstance = new DominoGameState(firstInstance);

        Domino tile = new Domino(1,2);
        String name = null;
        Player player = new Player(null);
        firstInstance.placeDomino(tile, player);
        firstInstance.drawDomino(player);

        ArrayList<Integer> board;
        board = firstInstance.getBoard();
        //board.add(tile);
    }

    public void addPlayer() {

    }
    public void removePlayer() {

    }

}
