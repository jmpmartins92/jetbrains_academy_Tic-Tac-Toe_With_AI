package tictactoe;

/**
 * Class that defines a player, which may be an AI at any difficulty, or a user.
 */
public class Player {


    String playerType;
    char symbol;
    Board board;
    AI ai;


    /**
     * Constructor for the default player, that is considered of the type user, will place the X symbol, which also
     * means it is the first to play.
     */
    public Player() {
        this.playerType = "user";
        this.symbol = 'X';
    }

    /**
     * Constructor for a player, which may be a user or an AI of the specified difficulty, may be player 1 or 2,
     * it acknowledges the current board, as the AI requires a board to decide its moves. This will only matter if
     * the constructor creates a player of the type AI.
     *
     * @param playerType may be "user" for a human player, "easy" or any string of other AI difficulty levels.
     * @param symbol may be a char X or O, and will determine the turns that will play (odd ones for X and O even ones).
     * @param board is the current board state.
     */
    public Player(String playerType, char symbol, Board board) {
        this.playerType = playerType;
        this.symbol = symbol;
        this.board = board;
        this.ai = new AI(board);
    }


    /**
     * Getter for the playerType
     *
     * @return string "user" for a human player, "easy" or any string of other AI difficulty levels.
     */
    public String getPlayerType() {
        return playerType;
    }

    /**
     * Getter for the symbol of the player
     *
     * @return char X or O, depending if it gets the symbol of the first or the second player.
     */
    public char getSymbol() {
        return symbol;
    }
}
