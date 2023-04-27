package com.example.dominoes;

public class RotateDomino {

    public int UP (int row) {
        return (row-1);
    }

    public int RIGHT (int col) {
        return (col+1);
    }

    public int DOWN (int row) {
        return (row+1);
    }

    public int LEFT (int col) {
        return (col-1);
    }

}
