package tictactoe;

import java.util.Scanner;


/**
 * Class that requests and treats the given inputs, namely boardStates and coordinates.
 */
public class InputRequests {

    /**
     * The user is asked for a boardState, which will then be processed and tested to check if it is within the possible
     * realm of possibilities. If the tests fail, an informative error message will be displayed, and the user will
     * again be asked for a boardState, until a valid boardState is provided.
     *
     * @return a valid boardState as a string, already treated for usage by other methods, and free of possible error
     * causing defects.
     */
    public static String boardStateRequest() {
        String input = "";
        int errorCode = 0;
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
     *
     * @return the boardState ready for further processing, as a String.
     */
    private static String boardStateInputTreatment() {
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
     * @param board the game board, required to guess which will be the symbol representing the new coordinate.
     * @return a valid coordinate already treated for usage by other methods, and free of possible error
     * causing defects.
     */
    public static Coordinate coordinateRequest(Board board) {
        String input = "";
        int errorCode = 0;
        do {
            System.out.println("Enter the coordinates: ");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            InputCheck inputCheck = new InputCheck(input, 0);
            inputCheck.inputCoordinateCheck(board);
            errorCode = inputCheck.getErrorCode();
            ErrorList errorList = new ErrorList(errorCode);
            System.out.print(errorList.errorMessage());
        } while (errorCode != 0);
        Coordinate coordinate = new Coordinate(Character.getNumericValue(input.charAt(0)),
                Character.getNumericValue(input.charAt(2)), ' ');
        coordinate.setCoordinateSymbol(board);
        return coordinate;
    }
}
