package tictactoe;

public class Main {
    public static void main(String[] args) {


        Board board = new Board();
        AI easyAI = new AI("easy", board);
        Rounds gameRound = new Rounds(true, false, board, easyAI);
        gameRound.fullGame();

    }
}
