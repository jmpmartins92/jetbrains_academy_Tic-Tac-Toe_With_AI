package tictactoe;

/**
 * Class that displays a menu that allows the user to choose the players, difficulties of AI, or exit the program.
 */
public class Menu {

    String command;
    String player1;
    String player2;

    /**
     * Constructor for the menu, which will have a command, that can be either "start" or "exit", and the role of each
     * player, which can both be any combination of "user" and difficulty level of the AI.
     *
     * @param command string that will start or close the game;
     * @param player1 properties of player 1
     * @param player2 properties of player 2;
     */
    public Menu(String command, String player1, String player2) {
        this.command = command;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Constructor for the menu that takes an already tested and prepared array of strings, that will start the program
     * as pretended.
     *
     * @param parameters array of 3 strings already tested and treated. In this case, the command will always be start.
     */
    public Menu(String[] parameters) {
        this.command = parameters[0];
        this.player1 = parameters[1];
        this.player2 = parameters[2];
    }

    public Menu() {
        this.command = "start";
        this.player1 = "user";
        this.player2 = "user";

    }

    /**
     * Getter of the info related to player1, namely if it is a user or the AI, and if so, on which difficulty.
     *
     * @return a string that defines player1;
     */
    public String getPlayer1() {
        return player1;
    }

    /**
     * Setter for player 1, which changes its type accordingly to the specified one.
     *
     * @param player1 String that may be "user" or any of the AI difficulties.
     */
    public void setPlayer1(String player1) {
        this.player1 = player1;
    }


    /**
     * Getter of the info related to player2, namely if it is a user or the AI, and if so, on which difficulty.
     *
     * @return a string that defines player2;
     */
    public String getPlayer2() {
        return player2;
    }

    /**
     * Setter for player 2, which changes its type accordingly to the specified one.
     *
     * @param player2 String that may be "user" or any of the AI difficulties.
     */
    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

}


