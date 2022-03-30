package com.company;

import java.util.ArrayList;

public class Pawn extends Piece{

    public Pawn(Board board, char color) {
        super(board, color, 'p');
    }

    public ArrayList<Move> getMoves() {
        updatePosition();
        int row=getRow();
        int column=getColumn();
        ArrayList<Move> moves=new ArrayList<>();
        int colorMultiplier=1;//-1 for black
        if(getColor()=='b'){
            colorMultiplier=-1;
        }
        if(board.getPiece(row+colorMultiplier,column)==null){
            moves.add(new Move(this,row+colorMultiplier,column));
            if (!hasMoved&&board.getPiece(row+colorMultiplier,column)==null){
                moves.add(new Move(this,row+2*colorMultiplier,column));
            }
        }
        if(column-1>=0&&board.getPiece(row+colorMultiplier,column-1)!=null&&board.getPiece(row+colorMultiplier,column-1).getColor()!=getColor()){
            moves.add(new Move(this,row+colorMultiplier,column-1));
        }
        if(column+1<8&&board.getPiece(row+colorMultiplier,column+1)!=null&&board.getPiece(row+colorMultiplier,column+1).getColor()!=getColor()){
            moves.add(new Move(this,row+colorMultiplier,column+1));
        }
        return moves;
    }
}
