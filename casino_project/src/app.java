import java.util.Scanner;

public class app {
	private static Blackjack<?> thisGameBlackjack;
    public static void main(String[] args) {
        // TODO make interface and interactions for setting how many decks here
        // for now will just ask in command line
        Scanner consoleScnr = new Scanner(System.in);
        System.out.println("how many decks combined?");
        String numberDeckStr = consoleScnr.nextLine();
        Integer tempint = null;
        try {
            tempint = Integer.parseInt(numberDeckStr);
        } catch (Exception e) {
            // TODO: handle exception
        }
        thisGameBlackjack = new Blackjack<Object>(tempint);
        Boolean keepPlayingBoolean = true;
        Boolean keepsettingsBoolean = true;
        while (keepPlayingBoolean) {
            while (keepsettingsBoolean) {
                while (thisGameBlackjack.gamePlayingBoolian()) {
                	//TODO: this needs to check game state, play out turns and handle player input
                	
                }
            }
        }
        consoleScnr.close();
    }
}
