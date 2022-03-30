import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(Board board, char color) {
        super(board, color,'n');
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
