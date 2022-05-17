import java.util.ArrayList;

public class Knight extends Piece{
    public static final int[][] key=new int[][]{{-1,-2},{-2,-1},{1,-2},{2,-1},{1,2},{2,1},{-1,2},{-2,1},};
    public Knight(Board board, char color) {
        super(board, color,'n');
    }

    public ArrayList<Move> getMoves() {
        updatePosition();
        int row=getRow();
        int column=getColumn();
        ArrayList<Move> moves=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int[] key=Knight.key[i];
            if(row+key[0]>=0&&row+key[0]<8&&column+key[1]>=0&&column+key[1]<8
                    &&(board.getPiece(row+key[0],column+key[1])==null||board.getPiece(row+key[0],column+key[1]).getColor()!=this.getColor())){
                Move newMove=new Move(this,row+key[0],column+key[1]);;
                if(new Board(board,newMove).getKing(this.getColor())!=null&&!new Board(board,newMove).getKing(this.getColor()).isInCheck()){
                    moves.add(newMove);
                }

            }
        }
        return moves;
    }
}
