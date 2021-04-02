package tictactoe;

/**
 * Class that defines a coordinate of the board game, with a specific symbol.
 */
public class Coordinate {

    int yCoordinate;
    int xCoordinate;
    char symbol;

    /**
     * Constructor to set X and Y coordinates, as well as its representation in the game board.
     *
     * @param yCoordinate y axis value for the coordinate.
     * @param xCoordinate x axis value for the coordinate.
     * @param symbol coordinate's representation on the game board.
     */
    public Coordinate (int yCoordinate, int xCoordinate, char symbol) {
        this.yCoordinate = yCoordinate;
        this.xCoordinate = xCoordinate;
        this.symbol = symbol;
    }

    /**
     * Getter for the symbol variable.
     *
     * @return symbol variable as a char.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Setter for the symbol variable.
     *
     * @param symbol Char representing the coordinate on the game board.
     */
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Getter for the X axis value of the coordinate.
     *
     * @return X axis value as an int.
     */
    public int getxCoordinate() {
        return xCoordinate;
    }


    /**
     * Setter for the X axis value of the coordinate.
     *
     * @param xCoordinate Int indicating the X axis value.
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }


    /**
     * Getter for the Y axis value of the coordinate.
     *
     * @return Y axis value as an int.
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Setter for the Y axis value of the coordinate.
     *
     * @param yCoordinate Int indicating the Y axis value.
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Creates an array with the Y and X values of a coordinate.
     *
     * @return coordinates represented as an array of ints, in the form (Y, X).
     */
    public int[] getCoordinate() {
        return new int[]{this.getyCoordinate(), this.getxCoordinate()};
    }

    /**
     * Setter for all coordinate variables
     *
     * @param yCoordinate Int indicating the Y axis value.
     * @param xCoordinate Int indicating the X axis value.
     * @param symbol Char representing the coordinate on the game board.
     */
    public void setCoordinate(int yCoordinate, int xCoordinate, char symbol) {
        setyCoordinate(yCoordinate);
        setxCoordinate(xCoordinate);
        setSymbol(symbol);
    }


    /**
     * Finds which symbol (X or O) should be set for a coordinate, depending on the present number of X and O characters
     * on the game board.
     * If there are more X's than O's on the board, sets the symbol to O. Otherwise, sets it to X.
     *
     * @param board the game board.
     *
     *
     * @Deprecated
     * The symbol of the coordinates provided by the user will now always be X, while the AI always plays O.
     */
    @Deprecated
    public void setCoordinateSymbol(Board board) {
        String boardState = board.getBoardState();
        int numX = 0;
        int numO = 0;
        for (int index = 0; index < boardState.length(); index++) {
            if (boardState.charAt(index) == 'X') {
                numX++;
            }
            if (boardState.charAt(index) == 'O') {
                numO++;
            }
        }
        if (numX <= numO) {
            setSymbol('X');
        } else {
            setSymbol('O');
        }
    }



}
