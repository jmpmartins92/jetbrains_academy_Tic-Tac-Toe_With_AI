package tictactoe;

/**
 * Class that contains an error code, which is connected to helpful error messages, depending of the test that the input
 * failed
 */
public class ErrorList {

    int errorCode;

    /**
     * Constructor for the List of possible errors.
     *
     * @param errorCode a int value corresponding to a specific error
     */
    public ErrorList(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Creates an informative error message, according to the errorCode provided. If the errorCode is 0, it is means
     * that the tested input passed all tests, and should be accepted as valid.
     *
     * @return A string with information about the error.
     */
    public String errorMessage() {
        switch (errorCode) {
            case 1:
                return "\nIncorrect input length\n";
            case 2:
                return "\nInvalid input, should only include X, O or _ symbols\n";
            case 3:
                return "\nYou should enter numbers!\n";
            case 4:
                return "\nCoordinates should be from 1 to 3!\n";
            case 5:
                return "\nThis cell is occupied! Choose another one!\n";
            case 6:
                return "\nBad parameters!\n";
            case 0:
                return "";
            default:
                return "\nDid not check for errors\n";
        }
    }
}
