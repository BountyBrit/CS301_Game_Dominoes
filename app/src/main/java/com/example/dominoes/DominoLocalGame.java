package com.example.dominoes;
//dummy comment to commit
public class DominoLocalGame {
public class DominoLocalGame {
    private int lastWinner;
    private int currentWinner;
    private DominoGameState dgs;

    /**
     * This ctor creates a new game state
     */
    public DominoLocalGame() {
        this.dgs = new DominoGameState();
    }
    protected boolean makeMove(GameAction action) {
        if (action instanceof DominoPlaceAction){
            return false;
        } else if (action instanceof DominoPassAction){
            return false;
        }
    }
    public boolean placeTile(Tile tile, int side) {
        if (tile == null || side < 0 || side > 1) {
            // invalid arguments
            return false;
        }

        if (board.isEmpty()) {
            // placing the first tile, no need to check sides
            board.add(tile);
            return true;
        }

        Tile lastTile = board.getLast();
        int lastSide = (side == 0) ? lastTile.getRight() : lastTile.getLeft();

        if (lastSide != tile.getLeft() && lastSide != tile.getRight()) {
            // tile cannot be placed
            return false;
        }

        if (side == 0) {
            // place tile on the left side
            if (lastSide == tile.getLeft()) {
                tile.flip(); // flip tile to match orientation
            }
            board.addFirst(tile);
        } else {
            // place tile on the right side
            if (lastSide == tile.getRight()) {
                tile.flip(); // flip tile to match orientation
            }
            board.addLast(tile);
        }

        return true;
    }


    public boolean isRoundOver() {
        return false;
    }

    public boolean isGameOver() {
        return false;
    }

    public void addScore(){

    }

    public boolean isValid(){
        return false;
    }

    public void alternatePlayer(){

    }
}
