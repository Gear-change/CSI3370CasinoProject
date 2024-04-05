import java.util.Scanner;

import cards.card;
import games.Blackjack;

public class app {
	private static void printMultiString(String toPrint) {
		String[] tempStringArry = toPrint.split("/n");
		for(String temp : tempStringArry) {
			System.out.println(temp);
		}
	}
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
                			thisGameBlackjack.DiscardAll();
                			thisGameBlackjack.firstTurnDeal();
                			//first turn, we don't need input
                			break;
                		case "dealerTurn2":
                			card temp = thisGameBlackjack.dealerTurn2();
                			if(temp.getCardValNum() != 0) {
                				System.out.println("the Dealer draws a " + temp.getCardFullString());
                			}
                			break;
                		case "Play":
                			//this is the first turn, get player input
                			boolean temp10 = true;
                			System.out.println("what do you want to do now? 1 to hit, 2 to stand");
                			while (temp10) {
                				switch(consoleScnr.next()) {
                				case "1":
                					thisGameBlackjack.hitMe();
                					temp10 = false;
                					break;
                				case "2":
                					thisGameBlackjack.stand();
                					temp10 = false;
                					break;
                				default:
                					System.out.println("I'm sorry, 1 is hit and 2 is stand:");
                					break;
                				}
                			}
                			break;
                		case "Dealer":
                			thisGameBlackjack.dealerTurn();
                			break;
                		
                	}
                	printMultiString(thisGameBlackjack.getGameStateAsString());
                }
                thisGameBlackjack.DiscardAll();
                if(keepPlayingBoolean) {
                	String temp6 = thisGameBlackjack.getState();
                	switch(temp6) {
                	case "Dealer Win2":
                		temp6 = "The Dealer won this game.";
                		break;
                	case "Player Win":
                		temp6 = "You won this game.";
                		break;
                	case "Dealer Win1":
                		temp6 = "The Dealer has a Blackjack and you don't, you have lost.";
                		break;
                	case "Start":
                		temp6 = "You have gone bust and lost.";
                		break;
                	case "Dealer Busted":
                		temp6 = "You won, the Dealer went bust!";
                		break;
                	case "playerbusted":
                		temp6 = "You lose, you have gone bust!";
                		break;
                	default:
                		temp6 = "error, if you see this, blame justin";
                		break;
                	}
                	thisGameBlackjack.setState("Start");
                	System.out.println(temp6);
                	System.out.println("do you wanna play again? 1 for yes, 0 for no:");
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
    			thisGameBlackjack.gameEnded = false;
    			}
                }
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
    


