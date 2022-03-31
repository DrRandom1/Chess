import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Board board=new Board();
        Game game = new Game(board, new Player('w',board), new Player('b',board));
        while (!game.isGameOver()){
            game.getBoard().printBoardState();
            System.out.println("Enter a location");
            String input=scanner.nextLine();
            while(parsePosition(input)==null
                    ||board.getPiece(parsePosition(input))==null
                    ||!board.getPiece(parsePosition(input)).canMove()||
                    game.getCurrentPlayer().getPieces().contains(board.getPiece(parsePosition(input)))
            ){
                System.out.println("Invalid Move");
                input=scanner.nextLine();
            }
            ArrayList<Move> moves=board.getPiece(parsePosition(input)).getMoves();
            for (int i = 0; i < moves.size(); i++) {
                System.out.println((i+1)+". "+moves.get(i).getAmbiguousMoveNotation());
            }
            int moveSelection=scanner.nextInt();
            game.makeMove(moves.get(moveSelection-1));
        }
    }
    private static int @Nullable [] parsePosition(String string){
        int[] position=new int[2];
        if(string.length()!=2){
            return null;
        }
        switch(string.charAt(0)){
            case 'a'->position[1]=0;
            case 'b'->position[1]=1;
            case 'c'->position[1]=2;
            case 'd'->position[1]=3;
            case 'e'->position[1]=4;
            case 'f'->position[1]=5;
            case 'g'->position[1]=6;
            case 'h'->position[1]=7;
            default -> {
                return null;
            }
        }
        switch(string.charAt(1)){
            case '1'->position[0]=0;
            case '2'->position[0]=1;
            case '3'->position[0]=2;
            case '4'->position[0]=3;
            case '5'->position[0]=4;
            case '6'->position[0]=5;
            case '7'->position[0]=6;
            case '8'->position[0]=7;
            default -> {
                return null;
            }
        }
        return position;
    }
}
