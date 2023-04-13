package com.example.dominoes;

import com.example.game.GameComputerPlayer;
import com.example.game.infoMsg.GameInfo;
import java.util.Random;

/**
 *
 * AI for Domino's
 *
 * @Author Alejandro Varela
 * @Author Brit Dannen
 * @Author Jackson Smith
 *
 */
public class DominoComputerPlayer extends GameComputerPlayer {
    public DominoComputerPlayer(String initName) {super(initName);}

    @Override
    protected void receiveInfo(GameInfo info) {
        DominoGameState dgs = new DominoGameState((DominoGameState)info);
        if (dgs.getCurrentPlayer() != playerNum) {
            return;
        } //return
        Random rnd = new Random();
        //if (rnd.nextBoolean()) {
            sleep(2000);
            DominoPassAction dpsa = new DominoPassAction(this);
            this.game.sendAction(dpsa);
        //} else {
//            sleep(2000);
//            DominoPlaceAction dpa = new DominoPlaceAction(this);
//            this.game.sendAction(dpa);
//        }

    }
}
