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
                Move newMove=(new Move(this,row+key[0],column+key[1]));
                if(!new Board(board,newMove).getKing(this.getColor()).isInCheck()){
                    moves.add(newMove);
                }
            }
        }
        return moves;
    }
    public boolean isInCheck(){
        updatePosition();
        //bishop/queen
        int row=getRow();
        int column=getColumn();
        for (int i = 0; i < 4; i++) {
            int[] key=Bishop.key[i];
            int counter=1;
            while(row+counter*key[0]>=0&&row+counter*key[0]<8&&column+counter*key[1]>=0&&column+counter*key[1]<8
                    &&(board.getPiece(row+counter*key[0],column+counter*key[1])==null
                    ||board.getPiece(row+counter*key[0],column+counter*key[1]).getColor()!=this.getColor())) {
                if(board.getPiece(row+counter*key[0],column+counter*key[1])!=null&&(board.getPiece(row+counter*key[0],column+counter*key[1]).getName()=='b'
                        &&board.getPiece(row+counter*key[0],column+counter*key[1]).getName()=='q')){
                    return true;
                }
                counter++;
            }
        }
        //rook
        for (int i = 0; i < 4; i++) {
            int[] key=Rook.key[i];
            int counter=1;
            while(row+counter*key[0]>=0&&row+counter*key[0]<8&&column+counter*key[1]>=0&&column+counter*key[1]<8
                    &&(board.getPiece(row+counter*key[0],column+counter*key[1])==null
                    ||board.getPiece(row+counter*key[0],column+counter*key[1]).getColor()!=this.getColor())) {
                if((board.getPiece(row+counter*key[0],column+counter*key[1])!=null&&board.getPiece(row + counter * key[0], column + counter * key[1]).getName() == 'b'
                        && board.getPiece(row + counter * key[0], column + counter * key[1]).getName() == 'q')){
                    return true;
                }
                counter++;
            }
        }
        for (int i = 0; i < 8; i++) {
            int[] key=Knight.key[i];
            if(row+key[0]>=0&&row+key[0]<8&&column+key[1]>=0&&column+key[1]<8
                    &&(board.getPiece(row+key[0],column+key[1])!=null
                    &&board.getPiece(row+key[0],column+key[1]).getColor()!=this.getColor())
                    &&board.getPiece(row+key[0],column+key[1]).getName()=='n'){
                    return true;
            }
        }
        return false;
    }
}
