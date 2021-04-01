package tictactoe;

import java.util.Scanner;


/**
 * Class that requests and treats the given inputs, namely boardStates and coordinates.
 */
public class InputRequests {

    Board board;

    /**
     * Constructor for the requests made to the user.
     *
     * @param board which will be used to compare cells with provided input
     */
    public InputRequests(Board board) {
        this.board = board;
    }



    /**
     * The user is asked for a boardState, which will then be processed and tested to check if it is within the possible
     * realm of possibilities. If the tests fail, an informative error message will be displayed, and the user will
     * again be asked for a boardState, until a valid boardState is provided.
     * @deprecated
     * The gameboard initial state will not be provided by the user anymore. Instead, the board will always be empty
     * at the start of a round
     *
     * @return a valid boardState as a string, already treated for usage by other methods, and free of possible error
     * causing defects.
     */
    @Deprecated
    public String boardStateRequest() {
        String input;
        int errorCode;
        do {
            System.out.println("Enter the cells: ");
            input = boardStateInputTreatment();
            InputCheck inputCheck = new InputCheck(input, 0);
            inputCheck.initialBoardStateCheck();
            errorCode = inputCheck.getErrorCode();
            ErrorList errorList = new ErrorList(errorCode);
            System.out.print(errorList.errorMessage());
        } while (errorCode != 0);
        return input;
    }

    /**
     * Scans the input boardState and treats it to be further processed and analyzed.
     * @deprecated
     * This method supports the previous method boardStateRequest, which is also deprecated.
     *
     * @return the boardState ready for further processing, as a String.
     */
    @Deprecated
    private String boardStateInputTreatment() {
        Scanner scanner = new Scanner(System.in);
        String boardState = scanner.nextLine();
        String stringTreated = boardState.toUpperCase();
        stringTreated = stringTreated.replace("_", " ");
        return stringTreated;
    }

    /**
     * The user is asked for a coordinate, which will then be processed and tested to check if it is within the possible
     * realm of possibilities. If the tests fail, an informative error message will be displayed, and the user will
     * again be asked for a coordinate, until a valid coordinate is provided.
     *
     * @return a valid coordinate already treated for usage by other methods, and free of possible error
     * causing defects.
     */
    public Coordinate coordinateRequest() {
        String input;
        int errorCode;
        do {
            System.out.println("\nEnter the coordinates: ");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            InputCheck inputCheck = new InputCheck(input, 0);
            inputCheck.inputCoordinateCheck(board);
            errorCode = inputCheck.getErrorCode();
            ErrorList errorList = new ErrorList(errorCode);
            System.out.print(errorList.errorMessage());
        } while (errorCode != 0);
        return new Coordinate(Character.getNumericValue(input.charAt(0)),
                Character.getNumericValue(input.charAt(2)), 'X');
    }
}
