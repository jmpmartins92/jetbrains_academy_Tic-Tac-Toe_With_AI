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
     * Chooses the difficulty level of the move to be taken by the AI, between an easy, medium or hard move.
     */
    public void aiMove(Player player) {
        switch (this.getLevel()) {
            case "easy":
                levelMoveEasy(player, board);
                break;
            case "medium":
                levelMoveMedium(player, board);
                break;
            case "hard":
                levelMoveHard(player, board);
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
     * @param board the game board on which the move is occurring.
     */
    private void levelMoveEasy(Player player, Board board) {
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
     * @param player The current player making the move.
     * @param board the game board on which the move is occurring.
     */
    private void levelMoveMedium(Player player, Board board) {
        if (testPossibleWin(player, true, board) != 1) {
            if(testPossibleWin(player, false, board) != -1) {
                levelMoveEasy(player, board);
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
     * @param board the game board on which the move is occurring.
     * @return int +1 if a move is a win, -1 if it is a counter win, 0 if no win or loss is possible at that point.
     */
    private int testPossibleWin (Player player, boolean isCurrentPlayer, Board board) {
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
                    return 1;
                } else if (testingGameState.getGameState().equals(symbol + " wins") && !isCurrentPlayer) {
                    auxSB.replace(cellNum, cellNum + 1, Character.toString(player.getSymbol()));
                    board.setBoardState(auxSB.toString());
                    return -1;
                } else {
                    testingBoard.setBoardState(board.getBoardState());
                    auxSB.replace(0, 9, testingBoard.getBoardState());

                }
            }
        }
        return 0;
    }


    /**
     * Algorithm consisting in a recursive method that will test all possible move cases, attributing a score to the
     * result of each of the possible end states of a board. It will choose the best move by checking it's own best move
     * and also assuming the opponent will always also play the best moves. This results in an AI that will either win
     * or draw, never lose.
     *
     * @param board The current board that is being recursively tested.
     * @param round The number of rounds that have already gone by.
     * @param isCurrentPlayer Boolean value that tests the possible moves by assuming the role of itself, or of the
     *                        opponent.
     * @param player Is the player, which might be playing with either X or O symbol.
     * @return A int score of the best possible move, recreating the board with the best chances to win (for either self
     * or the opponent).
     */
    private int minimax(Board board, int round, boolean isCurrentPlayer, Player player) {
        Board testingBoard = new Board(board.getBoardState());
        GameState testingGameState = new GameState("Game not finished", testingBoard);
        int score;

        testingGameState.checkGameState();
        if (testingGameState.getGameState().equals(player.getSymbol() + " wins")) {
            score = 100;
            return score;
        } else if (testingGameState.getGameState().equals(opponentsSymbol(player) + " wins")) {
            score = -100;
            return score;
        } else if (testingGameState.getGameState().equals("Draw")) {
            score = 0;
            return score;
        }


        if (isCurrentPlayer) {
            int best = Integer.MIN_VALUE;
            isCurrentPlayer = !isCurrentPlayer;
            for (int cellNum = 0; cellNum < 9; cellNum++) {
                StringBuilder testBoardState = new StringBuilder();
                testBoardState.append(testingBoard.getBoardState());
                if (testingBoard.getBoardState().charAt(cellNum) == ' ') {
                    testBoardState.replace(cellNum, cellNum + 1, Character.toString(player.getSymbol()));
                    testingBoard.setBoardState(testBoardState.toString());
                    round++;
                    best = Math.max(best, minimax(testingBoard, round, isCurrentPlayer, player));

                    testBoardState.replace(cellNum, cellNum + 1, " ");
                    testingBoard.setBoardState(testBoardState.toString());
                    }
                }
            return best;
            } else {
            int best = Integer.MAX_VALUE;
            isCurrentPlayer = !isCurrentPlayer;
            for (int cellNum = 0; cellNum < 9; cellNum++) {
                StringBuilder testBoardState = new StringBuilder();
                testBoardState.append(testingBoard.getBoardState());
                if (testingBoard.getBoardState().charAt(cellNum) == ' ') {
                    testBoardState.replace(cellNum, cellNum + 1, Character.toString(opponentsSymbol(player)));
                    testingBoard.setBoardState(testBoardState.toString());
                    round++;
                    best = Math.min(best, minimax(testingBoard, round, isCurrentPlayer, player));

                    testBoardState.replace(cellNum, cellNum + 1, " ");
                    testingBoard.setBoardState(testBoardState.toString());
                }
            }
            return best;
        }
    }


    /**
     * Makes a move as an AI with the hard difficulty level. This AI will always do the best move, by testing all
     * possible moves, predicting his best moves, and assuming the opponent will also do the best moves possible.
     *
     * @param player is the hard AI which is making the move.
     * @param board is the game board currently ongoing.
     */
    private void levelMoveHard(Player player, Board board) {
        int bestVal = Integer.MIN_VALUE;
        String bestBoardState = board.getBoardState();

        for (int cellNum = 0; cellNum < 9; cellNum++) {
            StringBuilder testBoardState = new StringBuilder();
            testBoardState.append(board.getBoardState());
            if (board.getBoardState().charAt(cellNum) == ' ') {
                testBoardState.replace(cellNum, cellNum + 1, Character.toString(player.getSymbol()));
                board.setBoardState(testBoardState.toString());
                int moveVal = minimax(board, 0, false, player);
                if (moveVal > bestVal) {
                    bestVal = moveVal;
                    bestBoardState = testBoardState.toString();
                }
                testBoardState.replace(cellNum, cellNum + 1, " ");
                board.setBoardState(testBoardState.toString());
            }
        }
        board.setBoardState(bestBoardState);
    }
}
