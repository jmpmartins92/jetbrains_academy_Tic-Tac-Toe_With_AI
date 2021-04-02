package tictactoe;

/**
 * Class that keeps track of the moves and state of the game, and connects the necessary steps to go through each round.
 */
public class Rounds {
    Boolean gameOver;
    Board board;
    AI ai;
    Menu menu;
    GameState gameState;




    /**
     * Constructor for Rounds class, which will have a different behaviour accordingly to its inputs.
     *
     * @param gameOver boolean that is set to true if the game as ended, putting and end to the program, otherwise it
     *        is set to false
     * @param board is the game board on which the rounds are taking action
     * @param ai is the difficulty level of the ai;
     * @param menu is menu that contains the parameters chosen by the user, to run the program in different modes
     * @param gameState is the situation of the current state, for example, if it is ongoing or if a player has won.
     */
    public Rounds(Boolean gameOver, Board board, AI ai, Menu menu, GameState gameState) {
        this.gameOver = gameOver;
        this.board = board;
        this.ai = ai;
        this.menu = menu;
        this.gameState = gameState;
    }

    /**
     * Constructor for Rounds class, that takes only the inital user input as a menu, by assuming a basic default case
     * for the other variables.
     *
     * @param menu
     */
    public Rounds(Menu menu) {
        this.gameOver = false;
        this.board = new Board();
        this.ai = new AI("easy", this.board);
        this.menu = menu;
        this.gameState = new GameState("Game not finished", board);
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
        gameState.checkGameState();
        if (gameState.getGameState().equals("Game not finished")) {
            setGameOver(false);
        } else {
            System.out.println(gameState.getGameState());
            setGameOver(true);
        }
    }

    /**
     * Sequence of actions taken during a user turn, namely requesting coordinates to the user, testing if input is
     * valid, and updating the game state.
     */
    public void userTurn(Player player) {
        Coordinate coordinate = board.coordinateRequest(player);
        board.setBoardState(coordinate);
        gameStaging();
    }

    /**
     * Sequence of actions taken during an AI turn, namely requesting coordinates to the user, testing if input is
     * valid, and updating the game state.
     */
    public void aITurn(Player player) {
        System.out.println("\nMaking move level \"" + ai.getLevel() + "\"");
        ai.aiMove(player);
        gameStaging();
    }


    /**
     * Creates a new player that will take the role of the first player, accordingly to the menu information.
     *
     * @return a new player, which plays the X symbol, and is the first to play.
     */
    public Player setPlayer1Info() {
        return new Player(menu.getPlayer1(), 'X', board);
    }

    /**
     * Creates a new player that will take the role of the second player, accordingly to the menu information.
     *
     * @return a new player, which plays the O symbol, and is the second to play.
     */
    public Player setPlayer2Info() {
        return new Player(menu.getPlayer2(), 'O', board);
    }

    /**
     * It makes a player move, which depends of the type of player at that turn (if human or AI).
     *
     * @param player dictates if the move should be taken by the user, or a AI at the defined difficulty
     */
    public void playerMove(Player player) {
        switch (player.getPlayerType()) {
            case ("user"):
                userTurn(player);
                break;
            case ("easy"):
                ai.setLevel("easy");
                aITurn(player);
                break;
        }
    }

    /**
     * method that runs the game until the game ends.
     */
    public void fullGame() {
        Player player1 = setPlayer1Info();
        Player player2 = setPlayer2Info();
        board.boardPrint();
        while (!getGameOver()) {
            playerMove(player1);
            if (!getGameOver()) {
                playerMove(player2);
            }
        }
    }
}
