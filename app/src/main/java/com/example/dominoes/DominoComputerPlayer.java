package com.example.dominoes;

import com.example.game.GameComputerPlayer;
import com.example.game.infoMsg.GameInfo;

import java.util.Random;

public class DominoComputerPlayer extends GameComputerPlayer {
    public DominoComputerPlayer(String initName) {super(initName);}

    @Override
    protected void receiveInfo(GameInfo info) {
         DominoGameState dgs = new DominoGameState((DominoGameState)info);
         if(dgs.getCurrentPlayer() != playerNum){
             return;
         }
         Random random = new Random();
         if(random.nextBoolean()) {
             sleep(1000);
             DominoPlaceAction dpa = new DominoPlaceAction(this);
             this.game.sendAction(dpa);
         }else{
             sleep(2000);
             DominoPassAction dpsa = new DominoPassAction(this);
             this.game.sendAction(dpsa);
         }
    }
}
