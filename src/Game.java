import java.util.ArrayList;

public class Game {
    private final Board board;
    private Player[] players=new Player[2];
    private int currentPlayer;
    public Move lastMove=null;


    public Game(Board board, Player white, Player black) {
        this.board = board;
        this.players[0] = white;
        this.players[1] = black;
        currentPlayer=0;
    }
    public Game(Board board, Player[] players) {
        this.board = board;
        this.players=players;
        currentPlayer=0;
    }

    public void makeMove(Move move){
        System.out.println("In game, "+ move.type);
        board.move(move);
        move.getPiece().hasMoved=true;
        if(currentPlayer==1){
            currentPlayer=0;
        } else{
            currentPlayer=1;
        }
        lastMove=move;
    }
    public boolean isGameOver(){
        for (int i = 0; i < 2; i++) {
            if (players[i].getPieces().size()==0){
                System.out.println("out of pieces");
                return true;
            }
            if (players[i].getMovablePieces().size()==0){
                System.out.println("out of moves");
                return true;
            }

        }
        return false;
    }
    public Board getBoard(){
        return board;
    }
    public Player getCurrentPlayer(){
        return players[currentPlayer];
    }
    public Player[] getPlayers(){
        return players;
    }
}
