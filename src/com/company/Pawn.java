package com.company;

import java.util.ArrayList;

public class Pawn extends Piece{
    private boolean hasMoved=false;
    private boolean hasMovedLastTurn=false;

    public Pawn(Board board, char color) {
        super(board, color, 'p');
    }

    public ArrayList<Move> getMoves() {
        updatePosition();
        ArrayList<Move> moves=new ArrayList<>();
        if (getColor()=='w'){
            moves.add(new Move(row+1,column,this));
        } else {
            moves.add(new Move(row-1,column,this));
        }
        if (!hasMoved){

        }
        return null;
    }

    public boolean isLegal(Move move) {
        return false;
    }
}
