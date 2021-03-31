package tictactoe;

public class Main {
    public static void main(String[] args) {


        String input = InputRequests.boardStateRequest();
        Board board = new Board(input);
        board.boardPrint();
        Coordinate coordinate = InputRequests.coordinateRequest(board);
        board.setBoardState(coordinate);
        GameState gameState = new GameState("Processing", board);
        gameState.checkGameState();
        board.boardPrint();
        System.out.println(gameState.getGameState());
    }
}
