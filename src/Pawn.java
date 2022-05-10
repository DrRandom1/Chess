import java.util.ArrayList;

public class Pawn extends Piece{
    public final int colorMultiplier;
    public Pawn(Board board, char color) {
        super(board, color, 'p');

        if(getColor()=='b'){
            this.colorMultiplier=1;
        } else {
            this.colorMultiplier=-1;//-1 for white

            //8 for white, 0 for black
            //Math.abs(cm-1)*4
        }
    }

    public ArrayList<Move> getMoves() {
        updatePosition();
        int row=getRow();
        int column=getColumn();
        ArrayList<Move> moves=new ArrayList<>();

        if(board.getPiece(row+colorMultiplier,column)==null){
            Move newMove=new Move(this,row+colorMultiplier,column);
            if(Math.abs(colorMultiplier+1)*4==row+colorMultiplier){
                moves.add(new Promotion(newMove));
            } else if(!new Board(board,newMove).getKing(this.getColor()).isInCheck()){
                moves.add(newMove);
            }
            if (!hasMoved&&board.getPiece(row+colorMultiplier,column)==null&&board.getPiece(row+2*colorMultiplier,column)==null){
                Move newMove1=new Move(this,row+2*colorMultiplier,column);
                if(!new Board(board,newMove1).getKing(this.getColor()).isInCheck()){
                    moves.add(newMove1);
                }
            }
        }
        //capture
        if(column-1>=0&&board.getPiece(row+colorMultiplier,column-1)!=null&&board.getPiece(row+colorMultiplier,column-1).getColor()!=getColor()){
            Move newMove=(new Move(this,row+colorMultiplier,column-1));
            if(Math.abs(colorMultiplier+1)*4==row+colorMultiplier){
                moves.add(new Promotion(newMove));
            } else if(!new Board(board,newMove).getKing(this.getColor()).isInCheck()){
                moves.add(newMove);
            }
        }
        if(column+1<8&&board.getPiece(row+colorMultiplier,column+1)!=null&&board.getPiece(row+colorMultiplier,column+1).getColor()!=getColor()){
            Move newMove=(new Move(this,row+colorMultiplier,column+1));
            if(Math.abs(colorMultiplier+1)*4==row+colorMultiplier){
                moves.add(new Promotion(newMove));
            } else if(!new Board(board,newMove).getKing(this.getColor()).isInCheck()){
                moves.add(newMove);
            }
        }

        //en croissant
        if(board.getLastMove()!=null&&column-1>=0&&board.getPiece(row,column-1)==board.getLastMove().getPiece()&&board.getLastMove().isPawnDoubleMove()){
            EnPassant newMove=(new EnPassant(this,row+colorMultiplier,column-1));
            if(!new Board(board,newMove).getKing(this.getColor()).isInCheck()){
                moves.add(newMove);
            }
        }
        if(board.getLastMove()!=null&&column+1<8&&board.getPiece(row,column+1)==board.getLastMove().getPiece()&&board.getLastMove().isPawnDoubleMove()){
            EnPassant newMove=(new EnPassant(this,row+colorMultiplier,column+1));
            if(!new Board(board,newMove).getKing(this.getColor()).isInCheck()){
                moves.add(newMove);
            }
        }
        return moves;
    }
}
