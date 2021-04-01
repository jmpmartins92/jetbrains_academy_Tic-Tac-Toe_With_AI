package tictactoe;

/**
 * Class that keeps track of the moves and state of the game, and connects the necessary steps to go through each round.
 */
public class Rounds {

    Boolean playerMove;
    Boolean gameOver;
    Board board;
    AI ai;

    /**
     * Constructor for Rounds class, which will have a different behaviour accordingly to its inputs.
     *
     * @param playerMove boolean that is set to true if the user is playing his round, and false if it's AI's turn.
     * @param gameOver boolean that is set to true if the game as ended, putting and end to the program, otherwise it
     *                 is set to false
     * @param board is the game board on which the rounds are taking action
     * @param ai  is the difficulty level of the ai;
     */
    public Rounds(Boolean playerMove, Boolean gameOver, Board board, AI ai) {
        this.playerMove = playerMove;
        this.gameOver = gameOver;
        this.board = board;
        this.ai = ai;
    }

    /**
     * Getter that informs if it is the AI's or User's turn.
     *
     * @return boolean true if user's turn, false if AI's turn.
     */
    public Boolean getPlayerMove() {
        return playerMove;
    }

    /**
     * Setter for the playerMove, effectively changing the turn of the round.
     *
     * @param playerMove true if it should be set to user's turn, false if it should be set to AI's turn;
     */
    public void setPlayerMove(Boolean playerMove) {
        this.playerMove = playerMove;
    }

    /**
     * Getter that informs if the game has ended, true if so, false if it did not end yet.
     *
     * @return boolean value true for game to be over, false if it should continue.
     */
    public Boolean getGameOver() {
        return gameOver;
    }

    /**
     * Setter for the GameOver, ending the game or if it should prooced.
     *
     * @param gameOver boolean value that sets the game as ended if true, and if it should continue if false.
     */
    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }


    /**
     * Method that analyzes the move to the game state made after a move, and updates info accordingly, namely updating
     * the gameState and choosing the next player (user or AI).
     */
    private void gameStaging() {
        board.boardPrint();
        GameState gameState = new GameState("Processing", board);
        gameState.checkGameState();
        if (gameState.getGameState().equals("Game not finished")) {
            setGameOver(false);
            if (!getPlayerMove()) {
                setPlayerMove(true);
            } else {
                setPlayerMove(false);
            }
        } else {
            System.out.println(gameState.getGameState());
            setGameOver(true);
        }
    }

    /**
     * Sequence of actions taken during a user turn, namely requesting coordinates to the user, testing if input is
     * valid, and updating the game state.
     */
    private void userTurn() {
        InputRequests requestCoords = new InputRequests(board);
        Coordinate coordinate = requestCoords.coordinateRequest();
        board.setBoardState(coordinate);
        gameStaging();
    }

    /**
     * Sequence of actions taken during an AI turn, namely requesting coordinates to the user, testing if input is
     * valid, and updating the game state.
     */
    private void aITurn() {
        System.out.println("\nMaking move level \"" + ai.getLevel() + "\"");
        ai.aiMove();
        gameStaging();
    }

    /**
     * Chooses which player should make the next move.
     */
    private void pickTurn() {
        if (getPlayerMove()) {
            userTurn();
        } else {
            aITurn();
        }
    }

    /**
     * method that runs the game until the game ends.
     */
    public void fullGame() {
        board.boardPrint();
        setPlayerMove(true);
        while (!getGameOver()) {
            pickTurn();
        }
        System.exit(0);
    }
}
