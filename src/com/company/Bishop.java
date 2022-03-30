import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Board board, char color) {
        super(board, color,'b');
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
