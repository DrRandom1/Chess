import java.util.ArrayList;

abstract class Piece {
    public final Board board;
    private final char color;
    private final char name;
    private int[] position;
    public boolean hasMoved=false;
    public boolean hasMovedLastTurn=false;

    public Piece(Board board,char color, char name){
        this.color=color;
        this.board=board;
        this.name=name;
    }

    public abstract ArrayList<Move> getMoves();
    public boolean isLegal(Move move){
        for (Move legalMove : getMoves()) {
            if (legalMove.getAmbiguousMoveNotation().equals(move.getAmbiguousMoveNotation())){
                return true;
            }
        }
        return false;
    }
    public char getName(){
        return name;
    }

    public char getColor() {
        return color;
    }

    public int[] getPosition() {
        updatePosition();
        return position;
    }
    public int getRow() {
        return position[0];
    }
    public int getColumn() {
        return position[1];
    }
    public void updatePosition() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if(board.getBoard()[row][column]!=null&&board.getBoard()[row][column]==this){
                    position=new int[]{row, column};
                }
            }
        }
    }

    public boolean canMove() {
        return !getMoves().isEmpty();
    }
    public void moved(){
        hasMoved=true;
    }
    public Piece clone(Board board){
        switch (this.name){
            case 'p':return new Pawn(board, color);
            case 'r':return new Rook(board, color);
            case 'n':return new Knight(board, color);
            case 'b':return new Bishop(board, color);
            case 'q':return new Queen(board, color);
            case 'k':return new King(board, color);
        }
        return null;
    }
}
