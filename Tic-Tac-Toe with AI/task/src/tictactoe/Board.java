package tictactoe;

import java.util.Scanner;

/**
 * Class that defines a board game, with a specific boardState of the type String.
 */
public class Board {

    String boardState;

    /**
     * Constructor to create a board, represents all the coordinates, defined by the parameter boardState.
     *
     * @param boardState String with 9 chars that can be either ' ', X or O. ' ' represents a empty cell. The position
     *                   of the coordinate is from top left to bottom right.
     */
    public Board(String boardState) {
        this.boardState = boardState;
    }

     static final String EMPTYBOARD= "         ";


    /**
     * Constructor to create an empty board, defining its boardState as a string with 9 ' ' (spaces), representing the
     * 9 existing coordinates as empty cells.
     */
    public Board() {
        this.boardState = EMPTYBOARD;
    }

    /**
     * Getter for the boardState of a game board.
     *
     * @return boardState variable as a string.
     */
    public String getBoardState() {
        return boardState;
    }

    /**
     * Setter for the boardState of a game board.
     *
     * @param boardState String representing the symbols of each coordinate of the game board.
     */
    public void setBoardState(String boardState) {
        this.boardState = boardState;
    }


    /**
     * Getter for a symbol of a specific pair of values representing a coordinate.
     *
     * @param y Int variable of the Y axis value.
     * @param x Int variable of the X axis value.
     * @return Char representing the symbol located at the given coordinate.
     */
    public char getCoordSymbol(int y, int x) {
        return getBoardState().charAt(boardStateCharIndex(y, x));
    }

    /**
     * Setter for the boardState, by updating a coordinate value. It does so by replacing the symbol of coordinate with
     * the symbol of the coordinate that represents the same position.
     *
     * @param coordinate Coordinate of the board game that is to be changed.
     */
    public void setBoardState(Coordinate coordinate) {
        int y = coordinate.yCoordinate;
        int x = coordinate.xCoordinate;
        StringBuilder updatedBoardState = new StringBuilder();
        updatedBoardState.append(boardState);
        updatedBoardState.replace(boardStateCharIndex(y, x), boardStateCharIndex(y, x) + 1, String.valueOf(coordinate.symbol));
        setBoardState(updatedBoardState.toString());
    }

    /**
     * Calculates the corresponding index of a string as a boardState, providing a pair of values that may represent a
     * coordinate.
     *
     * @param y Int variable of the Y axis value.
     * @param x Int variable of the X axis value.
     * @return index of a string, as an Int.
     */
    private int boardStateCharIndex (int y, int x) {
        return ((y - 1) * 3 + x - 1);
    }

    /**
     * prints the board on the Output Console, as a formatted grid, placing at each coordinate the corresponding symbol.
     */
    public void boardPrint() {
        System.out.print("\n---------");
        System.out.printf("\n| %s %s %s |",
                getCoordSymbol(1, 1), getCoordSymbol(1, 2), getCoordSymbol(1, 3));
        System.out.printf("\n| %s %s %s |",
                getCoordSymbol(2, 1), getCoordSymbol(2, 2), getCoordSymbol(2, 3));
        System.out.printf("\n| %s %s %s |",
                getCoordSymbol(3,1), getCoordSymbol(3, 2), getCoordSymbol(3, 3));
        System.out.print("\n---------\n");
    }

    /**
     * The user is asked for a coordinate, which will then be processed and tested to check if it is within the possible
     * realm of possibilities. If the tests fail, an informative error message will be displayed, and the user will
     * again be asked for a coordinate, until a valid coordinate is provided.
     *
     * @param player will decide which symbol, X or O, will be replacing the empty cell.
     * @return a valid coordinate already treated for usage by other methods, and free of possible error
     * causing defects.
     */
    public Coordinate coordinateRequest(Player player) {
        String input;
        int errorCode;
        do {
            System.out.println("\nEnter the coordinates: ");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            InputCheck inputCheck = new InputCheck(input, 0);
            inputCheck.inputCoordinateCheck(this);
            errorCode = inputCheck.getErrorCode();
            ErrorList errorList = new ErrorList(errorCode);
            System.out.print(errorList.errorMessage());
        } while (errorCode != 0);
        return new Coordinate(Character.getNumericValue(input.charAt(0)),
                Character.getNumericValue(input.charAt(2)), player.getSymbol());
    }
}
