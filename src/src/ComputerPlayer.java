import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class ComputerPlayer extends Player{
    private boolean inOpening=true;
    private int turn=0;
    private final double movesMultiplier=0.02;
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
    public Move findBestMove(){
        int depth=1;
        double bestEval=0;
        Move bestMove=null;
        if (getColor()=='w'){
            HashMap<Move, Double> moves=new HashMap<>();
            for (Piece piece: board.getPiecesOnBoard(this.getColor())) {
                for (Move move : piece.getMoves()) {
                    moves.put(move, findBestMove(new Board(board, move),  depth- 1));
                }
            }
            bestEval=-1000;
            for (Move move : moves.keySet()) {
                if(moves.get(move)>bestEval){
                    bestEval=moves.get(move);
                    bestMove=move;
                }
            }

        } else {
            HashMap<Move, Double> moves=new HashMap<>();
            for (Piece piece: board.getPiecesOnBoard(this.getColor())) {
                for (Move move : piece.getMoves()) {
                    moves.put(move, findBestMove(new Board(board, move),  depth- 1));
                }
            }
            bestEval=1000;
            for (Move move : moves.keySet()) {
                if(moves.get(move)<bestEval){
                    bestEval=moves.get(move);
                    bestMove=move;
                }
            }
        }
        return bestMove;
    }
    public double findBestMove(Board currentBoard, int depth){
        double bestEval=0;
        if (depth==0){
            bestEval=evaluatePosition(new Board(currentBoard, getBestMove()));
        } else if (depth%2==0){
            HashMap<Move, Double> moves=new HashMap<>();
            for (Piece piece: board.getPiecesOnBoard(this.getColor())) {
                for (Move move : piece.getMoves()) {
                    moves.put(move, findBestMove(new Board(currentBoard, move), depth - 1));
                }
            }
            bestEval=-1000;
            for (Move move : moves.keySet()) {
                if(moves.get(move)>bestEval){
                    bestEval=moves.get(move);
                }
            }

        } else {
            HashMap<Move, Double> moves=new HashMap<>();
            for (Piece piece: board.getPiecesOnBoard(this.getColor())) {
                for (Move move : piece.getMoves()) {
                    moves.put(move, findBestMove(new Board(currentBoard, move), depth - 1));
                }
            }
            bestEval=1000;
            for (Move move : moves.keySet()) {
                if(moves.get(move)<bestEval){
                    bestEval=moves.get(move);
                }
            }
        }
        return bestEval;
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
    public double evaluatePosition(Board board){
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
        int moves=0;
        for (Piece piece: board.getPiecesOnBoard(this.getColor())) {
            moves+=piece.getMoves().size();
        }
        for (Piece piece: board.getPiecesOnBoard(this.getColor()=='w'?'w':'b')) {
            moves-=piece.getMoves().size();
        }
        return eval-(moves*movesMultiplier);
    }

}
