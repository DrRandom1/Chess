import java.util.ArrayList;

public class Board {
    private final Piece[][] board = new Piece[8][8];
    private final ArrayList<Piece> pieces=new ArrayList<Piece>();

    private final char[] key=new char[]{'a','b','c','d','e','f','g','h'};
    public Board(){
        for (int i = 0; i < 8; i++) {
            switch(i){
                case 0:case 7:board[0][i]=new Rook(this,'w'); break;
                case 1:case 6:board[0][i]=new Knight(this,'w'); break;
                case 2:case 5:board[0][i]=new Bishop(this,'w'); break;
                case 3:board[0][i]=new Queen(this,'w'); break;
                case 4:board[0][i]=new King(this,'w'); break;
            }
            pieces.add(board[0][i]);
        }
        for (int i = 0; i < 8; i++) {
            switch(i){
                case 0:case 7:board[7][i]=new Rook(this,'b'); break;
                case 1:case 6:board[7][i]=new Knight(this,'b'); break;
                case 2:case 5:board[7][i]=new Bishop(this,'b'); break;
                case 3:board[7][i]=new Queen(this,'b'); break;
                case 4:board[7][i]=new King(this,'b'); break;
            }
            pieces.add(board[7][i]);

        }
        for (int i = 0; i < 8; i++) {
            board[1][i]=new Pawn(this,'w');
            pieces.add(board[1][i]);
        }
        for (int i = 0; i < 8; i++) {
            board[6][i]=new Pawn(this,'b');
            pieces.add(board[6][i]);
        }
    }
    public void printBoardState(){
        for (int i = 7; i >= 0; i--) {
            System.out.print("\u001b[1m"+(i+1)+"\u001b[0m  ");
            for (int j = 0; j < 8; j++) {
                Piece piece=board[i][j];
                System.out.print("\u001b[0m");
                if(i%2==j%2){
                    System.out.print("\u001b[48;5;72m");
                } else{
                    System.out.print("\u001b[48;5;40m");
                }
                if(piece==null){
                    System.out.print("  ");
                } else {
                    if (piece.getColor() == 'b') {
                        System.out.print("\u001b[1;38;5;232m ");
                    } else{
                        System.out.print("\u001b[1;38;5;231m ");
                    }
                    System.out.print(piece.getName());
                }
                System.out.print(" \u001b[0m");
            }
            System.out.print("\n");
        }
        System.out.println("    a  b  c  d  e  f  g  h");
    }

    public Piece[][] getBoard() {
        return board;
    }
    public Piece clear(int row, int column){
        Piece piece=board[row][column];
        board[row][column]=null;
        return piece;
    }
    public Piece clear(int[] pos){
        Piece piece=board[pos[0]][pos[1]];
        board[pos[0]][pos[1]]=null;
        return piece;
    }
    public int[] clear(Piece piece){
        int[] pos=piece.getPosition();
        board[pos[0]][pos[1]]=null;
        return pos;
    }
    public Piece move(Move move){
        //return captured piece
        Piece captured = clear(move.getPosition());
        board[move.getRow()][move.getColumn()]=clear(move.getPiece().getPosition());
        return captured;
    }

    public static void main(String[] args) {
        Board board=new Board();
        board.printBoardState();
    }

    public Piece getPiece(int [] position) {
        return board[position[0]][position[1]];
    }
    public Piece getPiece(int row, int column) {
        return board[row][column];
    }
    public ArrayList<Piece> getPieces(){
        return pieces;
    }
}
