package com.company;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(Board board, char color) {
        super(board, color,'r');
    }

    public ArrayList<Move> getMoves() {
        return null;
    }

    public boolean isLegal(Move move) {
        return false;
    }

    public boolean canMove() {
        return false;
    }

}
