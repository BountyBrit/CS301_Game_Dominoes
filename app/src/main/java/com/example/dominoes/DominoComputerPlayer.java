package com.example.dominoes;

import com.example.game.GameComputerPlayer;
import com.example.game.infoMsg.GameInfo;
import java.util.Random;

public class DominoComputerPlayer extends GameComputerPlayer {
    public DominoComputerPlayer(String initName) {super(initName);}

    @Override
    protected void receiveInfo(GameInfo info) {
        DominoGameState pgs = new DominoGameState((DominoGameState)info);
        if (pgs.getCurrentPlayer() != playerNum) {
            return;
        } //return
        Random rnd = new Random();
        if (rnd.nextBoolean()) {
            sleep(2000);
            DominoPassAction dpsa = new DominoPassAction(this);
            this.game.sendAction(dpsa);
        } else {
            sleep(2000);
            DominoPlaceAction dpa = new DominoPlaceAction(this);
            this.game.sendAction(dpa);
        }

    }
}
