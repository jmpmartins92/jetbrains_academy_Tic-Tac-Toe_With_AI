package tictactoe;

/**
 * Class that defines the current state of the game i.e. if it is still in progress, or there is already a winner, or
 * it is a draw.
 */
public class GameState {

    String gameState;
    Board board;

    /**
     * Constructor to set the game state, given by a string, which represents the state of the game, and the
     * corresponding game board
     *
     * @param gameState String representing the state of the game.
     * @param board game Board at that gameState.
     */
    public GameState(String gameState, Board board) {
        this.gameState = gameState;
        this.board = board;
    }

    /**
     * Getter for the gameState
     *
     * @return String representing the state of the game.
     */
    public String getGameState() {
        return gameState;
    }

    /**
     * Setter for the gameState
     *
     * @param gameState String representing the state of the game.
     */
    public void setGameState(String gameState) {
        this.gameState = gameState;
    }


    /**
     * Analyzes if there is a victor during the current gameState of the board. It first checks if there are 3 equal
     * symbols in a row at the diagonals of the board, then checks the rows and finally the columns. The symbols must
     * a playable one (i.e. it can't be an empty cell). The gameState is updated accordingly to the victorious symbol,
     * in case of a victory.
     *
     * @return boolean true if the game has been won, or false if it has not.
     */
    private boolean gameWon() {
        if ((board.getCoordSymbol(1, 1) == board.getCoordSymbol(2, 2)
                && board.getCoordSymbol(1, 1) == board.getCoordSymbol(3, 3)
                &&  board.getCoordSymbol(2,2) != ' ') ||
                (board.getCoordSymbol(3, 1) == board.getCoordSymbol(2, 2)
                        && board.getCoordSymbol(3, 1) == board.getCoordSymbol(1, 3)
                && board.getCoordSymbol(2,2) != ' ')) {
            setGameState(board.getCoordSymbol(2, 2) + " wins" );
            return true;
        }

        for (int y = 3; y > 0; y--) {
            if (board.getCoordSymbol(y, 3) == board.getCoordSymbol(y, 2) &&
                    board.getCoordSymbol(y, 3) == board.getCoordSymbol(y, 1) &&
                            board.getCoordSymbol(y,3) != ' ') {
                setGameState(board.getCoordSymbol(y, 3) + " wins" );
                return true;
            }
        }

        for (int x = 3; x > 0; x--) {
            if (board.getCoordSymbol(3, x) == board.getCoordSymbol(2, x) &&
                    board.getCoordSymbol(3, x) == board.getCoordSymbol(1, x) &&
                            board.getCoordSymbol(3,x) != ' ') {
                setGameState(board.getCoordSymbol(3, x) + " wins" );
                return true;
            }
        }

        return false;
    }


    /**
     * By checking if the game board still has empty cells, informs that the game has or not been finished. It only
     * holds true if the previous method gameWon() returns false, as not all cells have to be filled in order to a
     * game to be won and, therefore, finished.
     *
     * @return boolean true if the game has not been finished, and false if otherwise.
     */
    private boolean gameNotFinishedCheck() {
        boolean condition = board.getBoardState().contains(" ");
        if (condition) {
            setGameState("Game not finished");
        }
        return condition;
    }

    /**
     * Checks if there are any empty cells and, if not, the game state is set to a draw. As the previous method
     * gameNotFinishedCheck(), the result of this method only holds true if the method gameWon() returns false.
     *
     */
    private void drawCheck() {
        boolean condition = board.getBoardState().contains(" ");
        if (!condition) {
            setGameState("Draw");
        }
    }

    /**
     * Checks the gameState by checking each possible state, by a logical order to avoid false results. That is, it is
     * only possible to check if the game has not finished after checking if there was a victor. In the same way,
     * it is only possible to check if the game resulted in a draw after checking if it has not finished.
     */
    public void checkGameState() {
        if (!gameWon()) {
            if (!gameNotFinishedCheck()) {
                drawCheck();
            }
        }
    }
}
