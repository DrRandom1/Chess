import java.util.Random;

public class ComputerPlayer extends Player{
    private boolean inOpening=true;

    public ComputerPlayer(char color, Board board) {
        super(color, board);
        isComputerPlayer=true;
    }

    public Move getMove() {
        Random random=new Random();
        int randomPiece=random.nextInt(getMovablePieces().size());
        Piece piece =getMovablePieces().get(randomPiece);
        return piece.getMoves().get(random.nextInt(piece.getMoves().size()));
    }
    public static double evaluatePosition(Board board){
        double eval=0;
        for (Piece piece : board.getPieces()) {
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
