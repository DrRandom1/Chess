import java.util.ArrayList;

public class Game {
    private final Board board;
    private final Player[] players=new Player[2];
    private int currentPlayer;

    public Game(Board board, Player white, Player black) {
        this.board = board;
        this.players[0] = white;
        this.players[1] = black;
        currentPlayer=1;
    }

    public void makeMove(Move move){
        board.move(move);
        move.getPiece().moved();
        if(currentPlayer==1){
            currentPlayer=0;
        } else{
            currentPlayer=1;
        }
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


    public static void main(String[] args) {
        Board board=new Board();
        Game game = new Game(board, new Player('w',board), new Player('b',board));
        while(!game.isGameOver()){

        }
    }
}
