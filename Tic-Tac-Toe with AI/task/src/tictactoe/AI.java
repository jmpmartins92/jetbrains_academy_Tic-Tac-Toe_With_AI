package tictactoe;

import java.util.Random;

/**
 * Class AI that provides coordinates, following a specific set of rules defined by its difficulty level.
 */
public class AI {

    String level;
    Board board;

    /**
     * Constructor that creates an AI with a certain difficulty level.
     *
     * @param level difficulty as a String.
     * @param board on which the AI will be setting its move.
     */
    public AI(String level, Board board) {
        this.level = level;
        this.board = board;
    }

    /**
     * Default constructor that, if not provided with a specific level, it will be set as easy level.
     *
     * @param board on which the AI will be setting its move.
     */
    public AI(Board board) {
        this.level = "easy";
        this.board = board;
    }


    /**
     * Getter for the difficulty level of the selected AI.
     *
     * @return level of the AI as a string.
     */
    public String getLevel() {
        return level;
    }

    /**
     * Setter for the difficulty level of the selected AI.
     *
     * @param level string that must match the possible available difficulty levels.
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Chooses the difficulty level of the move to be taken by the AI. Already prepared to add new difficulty levels.
     */
    public void aiMove() {
        String level = getLevel();
        switch (level) {
            case "easy":
                levelMoveEasy();
                break;
            default:
                levelMoveEasy();
                break;
        }
    }

    /**
     * Plays a move on the difficulty level easy. at this difficulty, the AI will just choose a random empty cell as a
     * move. It does so by retrieving the current board state, picking a random cell which is represented as a char in
     * the board state string, and check if it is empty. If it is not empty, it will keep choosing other random cells,
     * otherwise, it will replace the current char at the chosen index to O, and updates the boardState.
     */
    private void levelMoveEasy() {
        Random random = new Random();
        StringBuilder auxSB = new StringBuilder();
        auxSB.append(board.getBoardState());
        boolean validMove;
        do {
            int randomIndex = random.nextInt(9);
            if (auxSB.charAt(randomIndex) == ' ') {
                auxSB.replace(randomIndex, randomIndex + 1, "O");
                validMove = true;
            } else {
                validMove = false;
            }
        } while (!validMove);
        board.setBoardState(auxSB.toString());
    }
}
