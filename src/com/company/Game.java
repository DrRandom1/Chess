package com.company;

public class Game {
    private final Board board;
    private final Player white;
    private final Player black;

    public Game(Board board, Player white, Player black) {
        this.board = board;
        this.white = white;
        this.black = black;
    }

    public void makeMove(Move move){
        board.move(move);
    }


    public static void main(String[] args) {
        Game game = new Game(new Board(), new Player(), new Player());
        for (int i = 0; i < 10000; i++) {
            System.out.println("\033[H\033[2J");
            game.board.printBoardState();
        }
    }
}
