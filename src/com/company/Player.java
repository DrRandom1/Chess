package com.company;

import java.util.ArrayList;

public class Player {
    private final ArrayList<Piece> pieces=new ArrayList<Piece>();
    private final char color;
    public ArrayList<Piece> getPieces(){
        return pieces;
    }
    public Player(char color,Board board){
        this.color=color;
        for (Piece piece : board.getPieces()) {
            if (piece.getColor()==color){
                pieces.add(piece);
            }
        }
    }
    public void remove(Piece piece){
        pieces.remove(piece);
    }
    public ArrayList<Piece> getMovablePieces(){
        ArrayList<Piece> movablePieces=new ArrayList<Piece>();
        for (Piece piece : pieces) {
            if(piece.canMove()){
                movablePieces.add(piece);
            }
        }
        return movablePieces;
    }
    public boolean isInCheck(){
        ArrayList<Piece> movablePieces=new ArrayList<Piece>();
        for (Piece piece : pieces) {
            if(piece.canMove()){
                movablePieces.add(piece);
            }
        }
        return false;
    }
}
