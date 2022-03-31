import java.util.ArrayList;

public class Bishop extends Piece {
    public static int[][]key=new int[][]{{-1,-1},{1,-1},{1,1},{-1,-1}};
    public Bishop(Board board, char color) {
        super(board, color,'b');
    }

    public ArrayList<Move> getMoves() {
        updatePosition();
        int row=getRow();
        int column=getColumn();
        ArrayList<Move> moves=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int[] key=Bishop.key[i];
            int counter=1;
            while(row+counter*key[0]>=0&&row+counter*key[0]<8&&column+counter*key[1]>=0&&column+counter*key[1]<8
                    &&(board.getPiece(row+counter*key[0],column+counter*key[1])==null||board.getPiece(row+counter*key[0],column+counter*key[1]).getColor()!=this.getColor())){

                moves.add(new Move(this,row+counter*key[0],column+counter*key[1]));
                if(board.getPiece(row+counter*key[0],column+counter*key[1])!=null&&board.getPiece(row+counter*key[0],column+counter*key[1]).getColor()!=this.getColor()){
                    counter+=9;
                }
                counter++;

            }
        }
        return moves;
    }
}
