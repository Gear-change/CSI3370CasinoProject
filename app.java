import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        // TODO make interface and interactions for setting how many decks here
        // for now will just ask in command line
        Scanner consoleScnr = new Scanner(System.in);
        System.out.println("how many decks combined?");
        String numberDeckStr = consoleScnr.nextLine();
        Integer tempint;
        try {
            tempint = Integer.parseInt(numberDeckStr);
        } catch (Exception e) {
            // TODO: handle exception
        }
        Blackjack thisgameBlackjack = new Blackjack(tempint);
        Boolean keepPlayingBoolean = true;
        Boolean keepsettingsBoolean = true;
        while (keepPlayingBoolean) {
            while (keepsettingsBoolean) {
                intTurns = 0;
                while (thisgameBlackjack.gamePlayingBoolian()) {

                }
            }
        }
    }
}
