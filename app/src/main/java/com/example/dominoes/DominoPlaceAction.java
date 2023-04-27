package com.example.dominoes;

import com.example.game.GamePlayer;
import com.example.game.actionMsg.GameAction;

/**
 *
 *Constructor for GameAction
 *
 * @author Alejandro Varela
 * @author Brit Dannen
 * @author Jackson Smith
 */
public class DominoPlaceAction extends GameAction {

    // Instance Variables
    private int Domino_Clicked;
    private int row_1;
    private int col_1;
    private int row_2;
    private int col_2;


    /** DominoPlaceAction
     *
     */
    public DominoPlaceAction(GamePlayer player, int dominoIndex, int row1, int col1, int row2, int col2) {
        super(player);
        this.Domino_Clicked = dominoIndex;
        this.row_1 = row1;
        this.col_1 = col1;
        this.row_2 = row2;
        this.col_2 = col2;
    }
    public int getRow_1() {
        return row_1;
    }

    public void setRow_1(int row_1) {
        this.row_1 = row_1;
    }

    public int getCol_1() {
        return col_1;
    }

    public void setCol_1(int col_1) {
        this.col_1 = col_1;
    }

    public int getRow_2() {
        return row_2;
    }

    public void setRow_2(int row_2) {
        this.row_2 = row_2;
    }

    public int getCol_2() {
        return col_2;
    }

    public void setCol_2(int col_2) {
        this.col_2 = col_2;
    }
    public int getDominoClicked(){
        return Domino_Clicked;
    }
}
