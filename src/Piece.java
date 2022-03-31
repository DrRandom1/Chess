import java.util.ArrayList;

abstract class Piece {
    public final Board board;
    private final char color;
    private final char name;
    private int id;
    private static int idIncrement=0;
    private int[] position;
    public boolean hasMoved=false;
    public boolean hasMovedLastTurn=false;

    public Piece(Board board,char color, char name){
        this.color=color;
        this.board=board;
        this.name=name;
        this.id=Piece.idIncrement;
        Piece.idIncrement++;
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

    public int getId() {
        return id;
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
                if(board.getBoard()[row][column]!=null&&board.getBoard()[row][column].getId()==id){
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
}
