package com.company;

public class Move {
    private final int[] position;
    private final Piece piece;


    public Move(int[] position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }
    public Move(int row, int column, Piece piece) {
        this.position = new int[]{row, column};
        this.piece = piece;
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
}
