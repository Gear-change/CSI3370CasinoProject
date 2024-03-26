import java.util.ArrayList;
import java.util.LinkedList;
public class Blackjack<cardcontainer> {
    public Boolean gameEnded;
    public Deck thisDeck;
    private cardContainer toDeal;
    private cardContainer dealerHand;
    private cardContainer playerHand;
    private cardContainer discard;
    public String stateOfPlay;
    private ArrayList<String> cardNameList = new ArrayList<String>();
    private ArrayList<Integer> cardVaList = new ArrayList<Integer>();
    private ArrayList<String> cardSuitsNameList = new ArrayList<String>();

    /**
	 * 
	 */
	public Blackjack(int deckNum) {
		// first we need to make a list of the cards in the deck to feed to deck.java
        cardNameList.clear();
        cardVaList.clear();
        cardSuitsNameList.clear();
        String[] cardset = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
                "Queen", "King" };
        String[] cardSuitsNames = { "Spades", "Hearts", "Clubs", "Diamonds" };
        Integer[] cardVals = new Integer[13];
        for (int i = 1; i <= cardVals.length; i++) {
            cardVals[i - 1] = i;
        }
        for(String thisString : cardset) {
        	cardNameList.add(thisString);
        }
        for(int thisInt : cardVals) {
        	cardVaList.add(thisInt);
        }
        for(String thisString : cardSuitsNames) {
        	cardSuitsNameList.add(thisString);
        }
        thisDeck = Deck(deckNum, cardNameList, cardVaList, cardSuitsNameList) ;
        // then we set the games state
        gameEnded = false;
        // then we make a shuffled deck
        toDeal = new cardContainer(thisDeck, true);
        stateOfPlay = "Start";
	}

    private Deck Deck(int deckNum, ArrayList<String> cardNameList2, ArrayList<Integer> cardVaList2,
			ArrayList<String> cardSuitsNameList2) {
		return Deck(deckNum, cardNameList, cardVaList, cardSuitsNameList);
	}

	public Boolean gamePlayingBoolian() {
        return !gameEnded;
    }
	public void DiscardAll() {
		LinkedList<card> cards1 = playerHand.getCards();
		LinkedList<card> cards2 = playerHand.getCards();
		LinkedList<card> cards3 = playerHand.getCards();
		cards1.addAll(cards2);
		cards1.addAll(cards3);
		for(card tempCard : cards1) {
			discard.addCardToContainer(tempCard, false);
		}
		
		}
    public void firstTurnDeal() {
        if (toDeal.getContainerSize() > 4) {
            playerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            dealerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            playerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            dealerHand.addCardToContainer(toDeal.drawFromContainer(), false);
        } else {
            toDeal = new cardContainer(thisDeck, true);
            playerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            dealerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            playerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            dealerHand.addCardToContainer(toDeal.drawFromContainer(), false);
        }
        int resultInt = this.checkDealerHand();
        int playerHandInit = this.checkPlayerHand();
        int dealerKnownCard = dealerHand.getCardValAt(0);

        // check for insurance/ dealer pocket blackjack and no player blackjack
        if (dealerKnownCard == 1 && playerHandInit != 21) {
            stateOfPlay = "Insurance";
            return;
        } else {
            // continue game as usual
            stateOfPlay = "Play";
        }
    }

    public void hitMe() {
        if (toDeal.getContainerSize() > 1) {
        } else {
            toDeal = new cardContainer(thisDeck, true);
        }
        int playerTotal = this.checkPlayerHand();
        if (playerTotal > 21) {
            // player has busted
            stateOfPlay = "busted";
        } else {
            stateOfPlay = "Play";
        }
    }

    public void stand() {
        stateOfPlay = "Dealer";
        return;
    }

    public String getGameStateAsString() {
        String toReturn = "";
        ArrayList<card> currentDealCardList = new ArrayList<card>();
        for (int i = 0; i < dealerHand.getContainerSize(); i++) {
            currentDealCardList.add(dealerHand.checkCardIfVisable(i));
        }
        toReturn.concat("The dealer's hand contains: ");
        for (card thiscard : currentDealCardList) {
            toReturn.concat(thiscard.getCardFullString() + " ");
        }
        ArrayList<card> currentPlayerCardList = new ArrayList<card>();
        for (int i = 0; i < playerHand.getContainerSize(); i++) {
            currentPlayerCardList.add(playerHand.checkCardIfVisable(i));
        }
        toReturn.concat("./n Your hand contains: ");
        for (card thiscard : currentPlayerCardList) {
            toReturn.concat(thiscard.getCardFullString() + " ");
        }
        toReturn.concat(".");
        return toReturn;
    }

    public void dealerTurn() {
        //TODO: this is where the dealer turn is resolved
    }

    private int checkDealerHand() {
        LinkedList<card> toExamine = dealerHand.getCards();
        ArrayList<Integer> dealerHandValList = new ArrayList<Integer>();
        for (card tempCard : toExamine) {
            dealerHandValList.add((tempCard).getCardValNum());
        }
        int totalDealerHand = 0;
        // special rules, first drop the face cards to 11
        for (Integer integer : dealerHandValList) {
            if (integer >= 11) {
                integer = 11;
            }
            totalDealerHand = totalDealerHand + integer;
        }
        // check for aces if the new total is not above 11
        if (totalDealerHand <= 12) {
            for (Integer integerVal : dealerHandValList) {
                if (integerVal == 1) {
                    totalDealerHand = totalDealerHand + 10;
                }
            }
        }
        return totalDealerHand;
    }

    private int checkPlayerHand() {
        LinkedList<card> toExamine = playerHand.getCards();
        ArrayList<Integer> playerHandValList = new ArrayList<Integer>();
        for (card tempCard : toExamine) {
            playerHandValList.add(tempCard.getCardValNum());
        }
        int totalPlayerHand = 0;
        // special rules, first drop the face cards to 11
        for (Integer integer : playerHandValList) {
            if (integer >= 11) {
                integer = 11;
            }
            totalPlayerHand = totalPlayerHand + integer;
        }
        // check for aces if the new total is not above 11
        if (totalPlayerHand <= 12) {
            for (Integer integerVal : playerHandValList) {
                if (integerVal == 1) {
                    totalPlayerHand = totalPlayerHand + 10;
                }
            }
        }
        return totalPlayerHand;
    }
}