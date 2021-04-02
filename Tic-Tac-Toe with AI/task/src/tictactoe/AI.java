package tictactoe;

import java.util.Random;

/**
 * Class AI that provides coordinates, following a specific set of rules defined by its difficulty level.
 */
public class AI  extends Player {

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
     * Chooses the difficulty level of the move to be taken by the AI, between an easy move or a medium move.
     */
    public void aiMove(Player player) {
        switch (this.getLevel()) {
            case "easy":
                levelMoveEasy(player);
                break;
            case "medium":
                levelMoveMedium(player);
                break;
            default:
                System.out.println("difficulty level not recognized");
                break;
        }
    }

    /**
     * Plays a move on the difficulty level easy. at this difficulty, the AI will just choose a random empty cell as a
     * move. It does so by retrieving the current board state, picking a random cell which is represented as a char in
     * the board state string, and check if it is empty. If it is not empty, it will keep choosing other random cells,
     * otherwise, it will replace the current char at the chosen index to O, and updates the boardState.
     *
     * @param player dictates which symbol will be replacing the empty cell.
     */
    private void levelMoveEasy(Player player) {
        Random random = new Random();
        StringBuilder auxSB = new StringBuilder();
        auxSB.append(board.getBoardState());
        boolean validMove;
        do {
            int randomIndex = random.nextInt(9);
            if (auxSB.charAt(randomIndex) == ' ') {
                auxSB.replace(randomIndex, randomIndex + 1, Character.toString(player.getSymbol()));
                validMove = true;
            } else {
                validMove = false;
            }
        } while (!validMove);
        board.setBoardState(auxSB.toString());
    }

    /**
     * plays a move in at the medium AI difficulty. It will first check if there are any possible winning moves (that
     * is, two of the current player symbols in a row. If so, it plays that same move. If not, it will check if, during
     * the next opponents move, if it has any possible winning move, by simulating that it is playing as with the
     * opponent symbol. If so, it will place it's own symbol instead, countering the win. If there is no possible
     * move that would stop the opponent from winning, it will then choose a random empty cell to place its move.
     *
     * @param player
     */
    private void levelMoveMedium(Player player) {
        if (!testPossibleWin(player, true)) {
            if(!testPossibleWin(player, false)) {
                levelMoveEasy(player);
            }
        }
    }


    /**
     * Gives back the opponents symbol, to run the tests necessary to counter move an opponent win.
     *
     * @param player is the player making the current move.
     * @return the char that represents the symbol of the opponent.
     */
    private char opponentsSymbol (Player player) {
        if (player.getSymbol() == 'X') {
            return 'O';
        } else {
            return 'X';
        }
    }

    /**
     * If a certain move will result in a win for either players, the method will evaluate if it is either a win for
     * that player, placing it's symbol in the corresponding empty cell, or a loss, which in that case, it will evaluate
     * if there could happen a win with the opposite symbol and, if so, places its own symbol instead.
     * It will return false if neither a win or a loss could happen.
     *
     * @param player is the player making the current move (player 1 or player 2)
     * @param isCurrentPlayer is true if it is evaluating a possible winning move for itself, and false if it is
     *                        evaluating possible winning moves of the opponent.
     * @return true if a move is either a win or a counter win, false if no win or loss is possible at that point.
     */
    private boolean testPossibleWin (Player player, boolean isCurrentPlayer) {
        char symbol;
        if (isCurrentPlayer) {
            symbol = player.getSymbol();
        } else {
            symbol = opponentsSymbol(player);
        }
        StringBuilder auxSB = new StringBuilder();
        auxSB.append(board.getBoardState());
        Board testingBoard = new Board(board.getBoardState());
        GameState testingGameState = new GameState("Game not finished", testingBoard);

        for (int cellNum = 0; cellNum < 9; cellNum++) {
            if (auxSB.charAt(cellNum) == ' ') {
                auxSB.replace(cellNum, cellNum + 1, Character.toString(symbol));
                testingBoard.setBoardState(auxSB.toString());
                testingGameState.checkGameState();
                if (testingGameState.getGameState().equals(symbol + " wins") && isCurrentPlayer) {
                    board.setBoardState(auxSB.toString());
                    return true;
                } else if (testingGameState.getGameState().equals(symbol + " wins") && !isCurrentPlayer) {
                    auxSB.replace(cellNum, cellNum + 1, Character.toString(player.getSymbol()));
                    board.setBoardState(auxSB.toString());
                    return true;
                } else {
                    testingBoard.setBoardState(board.getBoardState());
                    auxSB.replace(0, 9, testingBoard.getBoardState());

                }
            }
        }
        return false;
    }



}
