import java.lang.reflect.Array;
import java.util.ArrayList;

public class King extends Piece{
    public King(Board board, char color) {
        super(board, color,'k');
    }
    private static final int[][]key=new int[][]{{1,1},{1,0},{1,-1},{0,-1},{0,1},{-1,1},{-1,0},{-1,-1}};

    public ArrayList<Move> getMoves() {
        updatePosition();
        int row=getRow();
        int column=getColumn();
        ArrayList<Move> moves=new ArrayList<>();
        for (int i = 0; i <8 ; i++) {
            int[] key=King.key[i];
            if(row+key[0]>=0&&row+key[0]<8&&column+key[1]>=0&&column+key[1]<8
                    &&(board.getPiece(row+key[0],column+key[1])==null||board.getPiece(row+key[0],column+key[1]).getColor()!=getColor())){//does not check checkmate
                moves.add(new Move(this,row+key[0],column+key[1]));
            }
        }
        return moves;
    }
}
