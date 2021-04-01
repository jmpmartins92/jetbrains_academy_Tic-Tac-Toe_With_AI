package tictactoe;

/**
 * Class responsible for analyzing the input and reporting any errors. The input will not be accepted until it passes
 * all tests.
 */
public class InputCheck {

    String input;
    int errorCode;

    /**
     * Constructor for checking the input for errors.
     *
     * @param input String that the users provides.
     * @param errorCode int value that corresponds to a specific error, utilized for messages from ErrorList class.
     */
    public InputCheck(String input, int errorCode) {
        this.input = input;
        this.errorCode = errorCode;
    }

    /**
     * Getter for errorCode
     *
     * @return value of the errorCode as in int.
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Setter for errorCode
     *
     * @param errorCode Sets the current errorCode by the provided int value.
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Tests executed when the input is a boardState. It checks if the length is exactly 9, corresponding to the 9
     * different coordinates of the board. It checks if the input contains only valid chars, namely X, O and ' '.
     * If it passes both tests, sets the errorCode to 0, which corresponds to no errors. Otherwise, it will set the
     * errorCode to a specific value to provide the appropriate error message.
     * @deprecated
     * The user will no longer provide a boardState.
     *
     */
    @Deprecated
    public void initialBoardStateCheck() {
        if (input.length() != 9) {
            setErrorCode(1);
        } else if (!input.matches("^[XO ]+$")) {
            setErrorCode(2);
        } else {
            setErrorCode(0);
        }

    }

    /**
     * Tests executed when the input is a coordinate. It checks if the input contains nothing other than digits and ' '.
     * It checks if the input contains only numbers from 1 to 3, which are respectively the minimum and maximum position
     * of each axis. Checks if the provided coordinate is an empty cell or not, as the user cannot change cells that
     * were already played. If it passes all tests, sets the errorCode to 0, which corresponds to no errors. Otherwise,
     * it will set the errorCode to a specific value to provide the appropriate error message.
     *
     * @param board board game, which is required to check if the provided coordinate is an empty cell or not.
     */
    public void inputCoordinateCheck(Board board) {

        if (!input.matches("^[\\d ]+$")) {
            setErrorCode(3);
        } else if (!input.matches("^[123 ]+$")) {
            setErrorCode(4);
        } else if (board.getCoordSymbol(Character.getNumericValue(input.charAt(0)),
                Character.getNumericValue(input.charAt(2))) != ' ') {
            setErrorCode(5);
        } else {
            setErrorCode(0);
        }

    }

}
