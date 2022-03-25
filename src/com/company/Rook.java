package com.company;

import java.util.ArrayList;

public class Rook extends Piece {
    private boolean hasMoved=false;
    public Rook(Board board, char color) {
        super(board, color,'r');
    }

    public ArrayList<Move> getMoves() {
        return null;
    }

    public boolean isLegal(Move move) {
        return false;
    }

}
