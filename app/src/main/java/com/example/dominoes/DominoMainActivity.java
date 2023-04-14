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
 * Our status of our game is improving more since we changed the way we had initialized
 * the board, and its making our lives easier. One of these big changes is making our board
 * a 2D array . The board and the dominoes do get called
 * when clicked and were one step on having the board when clicked, be substituted for the domino
 * that has been clicked by the player, and with the AI choosing a random valid domino
 * to be places at the board.
 *
 * A big bug we have been facing is the human and computer player placing a domino. It is a problem
 * within the onClick on the main board, which is full of buttons. The action can't be sent properly
 * and our group doesn't understand what is happening and why we cannot send the action. The main
 * structure is there to place a domino, but the GUI is not updated. Our group has had a lot of
 * difficulties with placing a domino but we are incredibly close. We just could not make the
 * deadline of beta release for it. This bug could have been prevented if we finalized on how the
 * board is initialized and how the programs access the information of where the player is clicking.
 *
 * The pass button does work and both the human and computer player are able to pass their turn.
 * The game can technically end when a player has placed all of their dominoes, but the place domino
 * actions are being sent properly. The GUI has been updated since alpha. It clearly displays the
 * board and the player's hands accurately display's their dominoes. The dumb ai can place or pass
 * their turn, but sometimes places out of bounds.
 *
 * @author britdannen
 * @author Alejandro Varela
 * @author Jackson Smith
 *
 */
public class DominoMainActivity extends GameMainActivity{
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
