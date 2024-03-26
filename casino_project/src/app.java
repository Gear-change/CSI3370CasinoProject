import java.util.Scanner;
import games.Blackjack;

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
                	switch(thisGameBlackjack.getState()){
                		case "Stand-Off":
                			thisGameBlackjack.DiscardAll();
                			thisGameBlackjack.setState("Start");
                			System.out.println("We now have a stand-off, lets see who gits busted next round");
                		case "Start":
                			thisGameBlackjack.firstTurnDeal();
                			//first turn, we don't need input
                			break;
                		case "Dealer Win1":
                		case "Play":
                			//this is the first turn, get player input
                			boolean temp = true;
                			System.out.println("what do you want to do now? 1 to hit, 2 to stand");
                			while (temp) {
                				switch(consoleScnr.next()) {
                				case "1":
                					thisGameBlackjack.hitMe();
                					temp = false;
                					break;
                				case "2":
                					thisGameBlackjack.stand();
                					temp = false;
                					break;
                				default:
                					System.out.println("I'm sorry, 1 is hit and 2 is stand:");
                					break;
                				}
                			}
                			break;
                		case "Dealer":
                			thisGameBlackjack.dealerTurn();
                			thisGameBlackjack.DiscardAll();
                			break;
                		case "busted":
                			thisGameBlackjack.DiscardAll();
                			System.out.println("you have busted, you lose, do you wanna play again? 1 for yes, 0 for no:");
                			Integer temp1 = 2;
                			while(temp1 > 1 || temp1 < 0)
                			try {
								temp1 = consoleScnr.nextInt();
								System.out.println("come again");
							} catch (Exception e) {
								System.out.println("1 for yes and 0 for no");
							}
                			Integer temp2 = 2;
                			if (temp1 == 1) {
                				keepPlayingBoolean = true;
                				System.out.println("Do you wanna continue on this deck? 1 for yes, 0 for no:");
                				while(temp2 > 1 || temp2 < 0)
                        			try {
        								temp2 = consoleScnr.nextInt();
        								System.out.println("come again");
        							} catch (Exception e) {
        								System.out.println("1 for yes and 0 for no");
        						}
                				if (temp2 == 1) {
                					keepsettingsBoolean = true;
                				}
                				else {
                					keepsettingsBoolean = false;
                				}
                				} else { keepPlayingBoolean = false;keepsettingsBoolean = false;}
                			}
                			break;
                		
                	}
                if(keepPlayingBoolean) {
                System.out.println(thisGameBlackjack.getGameStateAsString());
                }}
            	if(keepPlayingBoolean) {
            		System.out.println("please enter how many decks you want:");
            		boolean temp4 = true;
            		Integer temp3 = null;
            		while (temp4) {
            			try {
            				temp3 = consoleScnr.nextInt();
            				temp4 = false;
            			} catch (Exception e) {
            				System.out.println("please enter a number:");
            			}
            		}
            		thisGameBlackjack = new Blackjack<Object>(temp3);
                    keepsettingsBoolean = true;
            	}
            	
                }
        	consoleScnr.close();        
    
    	}
        
        }
    


