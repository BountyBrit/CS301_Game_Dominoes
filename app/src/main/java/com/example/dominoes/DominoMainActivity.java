package com.example.dominoes;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.game.GameMainActivity;
import com.example.game.GamePlayer;
import com.example.game.LocalGame;
import com.example.game.config.GameConfig;
import com.example.game.config.GamePlayerType;

import java.util.ArrayList;

/** DominoMainActivity
 *
 * DominoMainActivity is the primary activity for Domino's
 *
 * @author britdannen
 * @author Alejandro Varela
 * @author Jackson Smith
 *
 */
public class DominoMainActivity extends GameMainActivity {
    // the port number that this game will use when playing over the network
    private static final int PORT_NUMBER = 2278;

    @Override
    public GameConfig createDefaultConfig() {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Domino has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new DominoHumanPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new DominoComputerPlayer(name);
            }});

        // Create a game configuration class for Domino:
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 4, "Domino", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer 1", 1); // player 2: a computer player
        defaultConfig.addPlayer("Computer 2", 1); // player 3: a computer player
        defaultConfig.addPlayer("Computer 3", 1); // player 4: a computer player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);

        return defaultConfig;
    }//createDefaultConfig

    @Override
    public LocalGame createLocalGame() {return new DominoLocalGame();}//createLocalGame
}
