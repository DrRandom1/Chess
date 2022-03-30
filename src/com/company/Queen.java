package com.company;

import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(Board board, char color) {
        super(board, color,'q');
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
