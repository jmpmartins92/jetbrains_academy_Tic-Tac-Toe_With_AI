package tictactoe;

public class Main {
    public static void main(String[] args) {

        InputRequests inputRequests = new InputRequests();
        while(true) {
            Menu menu = new Menu(inputRequests.inputInitialMenu());
            Rounds gameRound = new Rounds(menu);
            gameRound.fullGame();
        }
    }
}
