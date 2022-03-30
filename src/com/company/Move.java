package com.company;

public class Move {
    private final int[] position;
    private final Piece piece;
    private final String ambiguousMoveNotation;
    private final char[] key = new char[]{'a','b','c','d','e','f','g','h'};


    public Move(Piece piece, int[] position) {
        this.position = position;
        this.piece = piece;
        this.ambiguousMoveNotation = getAmbiguousMoveNotation();
    }
    public Move(Piece piece, int row, int column) {
        this.position = new int[]{row, column};
        this.piece = piece;
        this.ambiguousMoveNotation = getAmbiguousMoveNotation();
    }

    public int[] getPosition() {
        return position;
    }
    public Piece getPiece() {
        return piece;
    }
    public int getRow(){
        return position[0];
    }
    public int getColumn(){
        return position[1];
    }
    public String getAmbiguousMoveNotation(){
        if(piece.getName()=='p'){
            return key[position[1]]+""+(position[0]+1);
        } else{
            return piece.getName()+""+key[position[1]]+""+(position[0]+1);
        }
    }
}
