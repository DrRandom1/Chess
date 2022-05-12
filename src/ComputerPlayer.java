import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class ComputerPlayer extends Player{
    private boolean inOpening=true;

    public ComputerPlayer(char color, Board board) {
        super(color, board);
        isComputerPlayer=true;
    }

    public Move getRandomMove() {
        Random random=new Random();
        int randomPiece=random.nextInt(getMovablePieces().size());
        Piece piece =getMovablePieces().get(randomPiece);
        return piece.getMoves().get(random.nextInt(piece.getMoves().size()));
    }
    public Move findBestMove(int depth){
        Move bestMove=null;
        if (depth==0){
            bestMove=getBestMove();
        } else{
            HashMap<Move, Double> moves=new HashMap<>();
            for (Piece piece: board.getPiecesOnBoard(this.getColor())) {
                for (Move move : piece.getMoves()) {
                    moves.put(move,evaluatePosition(new Board(board,move)));
                }
            }
            bestMove=findBestMove(depth-1);
        }
        return bestMove;
    }
    public Move getBestMove() {
        HashMap<Move, Double> moves=new HashMap<>();
        for (Piece piece: board.getPiecesOnBoard(this.getColor())) {
            for (Move move : piece.getMoves()) {
                moves.put(move,evaluatePosition(new Board(board,move)));
            }
        }
        Move bestMove=null;
        switch(this.getColor()){
            case('w')->{
                double best=-1000;
                for (Move move : moves.keySet()) {
                    if(moves.get(move)>best){
                        bestMove=move;
                        best=moves.get(bestMove);
                    }
                }
            }
            case('b')->{
                double best=1000;
                for (Move move : moves.keySet()) {
                    if(moves.get(move)<best){
                        bestMove=move;
                        best=moves.get(bestMove);
                    }
                }
            }
        }
        return bestMove;
    }
    public Move getOppBestMove() {
        HashMap<Move, Double> moves=new HashMap<>();
        for (Piece piece: board.getPiecesOnBoard(this.getColor())) {
            for (Move move : piece.getMoves()) {
                moves.put(move,evaluatePosition(new Board(board,move)));
            }
        }
        Move bestMove=null;
        switch(this.getColor()){
            case('b')->{
                double best=-1000;
                for (Move move : moves.keySet()) {
                    if(moves.get(move)>best){
                        bestMove=move;
                        best=moves.get(bestMove);
                    }
                }
            }
            case('w')->{
                double best=1000;
                for (Move move : moves.keySet()) {
                    if(moves.get(move)<best){
                        bestMove=move;
                        best=moves.get(bestMove);
                    }
                }
            }
        }
        return bestMove;
    }
    public static double evaluatePosition(Board board){
        double eval=0;
        for (Piece piece : board.getPiecesOnBoard()) {
            //base piece value
            int val=piece.getColor()=='w'?1:-1;
            switch (piece.getName()){
                case 'p'->val*=1;
                case 'b'->val*=3;
                case 'n'->val*=3;
                case 'r'->val*=5;
                case 'q'->val*=9;
            }
            eval+=val;
        }
        return eval;
    }
}
