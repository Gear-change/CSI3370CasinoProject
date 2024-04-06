package games;

import java.util.ArrayList;
import java.util.LinkedList;
import cards.*;
import cardContainers.cardContainer;
import deckCards.Deck;

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
    public Integer insurance;

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
        for (String thisString : cardset) {
            cardNameList.add(thisString);
        }
        for (int thisInt : cardVals) {
            cardVaList.add(thisInt);
        }
        for (String thisString : cardSuitsNames) {
            cardSuitsNameList.add(thisString);
        }
        thisDeck = new Deck(deckNum, cardNameList, cardVaList, cardSuitsNameList);
        // then we set the games state
        gameEnded = false;
        // then we make a shuffled deck
        toDeal = new cardContainer(thisDeck, true);
        stateOfPlay = "Start";
        dealerHand = new cardContainer();
        playerHand = new cardContainer();
        discard = new cardContainer();
    }

    public String getState() {
        // this returns what state we are in
        return stateOfPlay;
    }

    public Boolean gamePlayingBoolian() {
        // this is a additional way to keep track of the game
        return !gameEnded;
    }

    public void DiscardAll() {
        // for the end of round resets
        LinkedList<card> cards1 = playerHand.getCards();
        LinkedList<card> cards2 = dealerHand.getCards();
        cards1.addAll(cards2);
        for (card tempCard : cards1) {
            discard.addCardToContainer(tempCard, false);
        }
        playerHand.clearContainer();
        dealerHand.clearContainer();
    }

    public void firstTurnDeal() {
        // this should deal the first hand
        if (toDeal.getContainerSize() > 4) {
            playerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            dealerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            playerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            dealerHand.addCardToContainer(toDeal.drawFromContainer(), false);
        } else {
            toDeal.addCardsFromContainer(discard, true);
            discard.clearContainer();
            playerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            dealerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            playerHand.addCardToContainer(toDeal.drawFromContainer(), true);
            dealerHand.addCardToContainer(toDeal.drawFromContainer(), false);
        }
        int resultInt = this.checkDealerHand();
        int playerHandInit = this.checkPlayerHand();
        int dealerKnownCard = dealerHand.getCardValAt(0);

        // check for insurance/ dealer pocket blackjack and no player blackjack
        if (dealerKnownCard == 1 && playerHandInit != 21 && resultInt == 21) {
            stateOfPlay = "Dealer Win1";
            gameEnded = true;
            this.DiscardAll();
            return;
        } else {
            // continue game as usual
            stateOfPlay = "Play";
        }
    }

    public cardContainer getDealerHand() {
        // returns dealer's hand
        return dealerHand;
    }

    public cardContainer getPlayersHand() {
        // returns the player's hand as a container, for getting thier card data
        return playerHand;
    }

    public void hitMe() {
        // this is for the player who wants another card
        if (toDeal.getContainerSize() > 1) {
            playerHand.addCardToContainer(toDeal.drawFromContainer(), true);
        } else {
            toDeal.addCardsFromContainer(discard, true);
            discard.clearContainer();
            playerHand.addCardToContainer(toDeal.drawFromContainer(), true);
        }
        int playerTotal = this.checkPlayerHand();
        if (playerTotal > 21) {
            // player has busted
            stateOfPlay = "playerbusted";
            System.out.println("you went bust.");
            gameEnded = true;
        } else {
            stateOfPlay = "Play";
        }
    }

    public void stand() {
        // dealers turn when the player is standing
        stateOfPlay = "Dealer";
        return;
    }

    public String getGameStateAsString() {
        // this returns who has what cards as a string.
        String toReturn = "";
        ArrayList<card> currentDealCardList = new ArrayList<card>();
        for (int i = 0; i < dealerHand.getContainerSize(); i++) {
            currentDealCardList.add(dealerHand.checkCardIfVisable(i));
        }
        toReturn = toReturn.concat("The dealer's hand contains: ");
        for (card thiscard : currentDealCardList) {
            toReturn = toReturn.concat(thiscard.getCardFullString()).concat(" ");
        }
        ArrayList<card> currentPlayerCardList = new ArrayList<card>();
        for (int i = 0; i < playerHand.getContainerSize(); i++) {
            currentPlayerCardList.add(playerHand.checkCardIfVisable(i));
        }
        toReturn = toReturn.concat("./n Your hand contains: ");
        for (card thiscard : currentPlayerCardList) {
            toReturn = toReturn.concat(thiscard.getCardFullString() + " ");
        }
        toReturn = toReturn.concat(".");
        return toReturn;
    }

    public void setState(String toSet) {
        // this is to reset for use by the playhandler classes
        stateOfPlay = toSet;
        return;
    }

    public card dealerTurn2() {
        // this is where the dealers hand is resolved, after a few cycles through this
        // thing. this should spit out how and if the player/dealer wins.
        if (this.checkDealerHand() < 17) {
            card tempCard = toDeal.drawFromContainer();

            dealerHand.addCardToContainer(tempCard, true);
            return tempCard;
        } else {
            card tempCard = new card();
            if (this.checkDealerHand() > 21) {
                stateOfPlay = "Dealer Busted";
                gameEnded = true;
            } else {
                if (this.checkDealerHand() > this.checkPlayerHand()) {
                    stateOfPlay = "Dealer Win2";
                    gameEnded = true;
                }
                if (this.checkDealerHand() < this.checkPlayerHand()) {
                    stateOfPlay = "Player Win";
                    gameEnded = true;
                }
                if (this.checkDealerHand() == this.checkPlayerHand()) {
                    stateOfPlay = "Stand-Off";
                }
            }
            return tempCard;
        }
    }

    public void dealerTurn() {
        // TODO: this is where the dealer turn is started, flipping the face down card
        // and setting the state for the rest of the turn
        // printout for the dealers initial hand
        card temp5 = dealerHand.getCardFromIndex(1);
        System.out.println("the dealer flips his card, revealing a " + temp5.getCardValNameString());
        dealerHand.setVisability(1, true);
        stateOfPlay = "dealerTurn2";
    }

    private int checkDealerHand() {
        // this is to get the total number of the dealer's hand
        LinkedList<card> toExamine = dealerHand.getCards();
        ArrayList<Integer> dealerHandValList = new ArrayList<Integer>();
        for (card tempCard : toExamine) {
            dealerHandValList.add((tempCard).getCardValNum());
        }
        int totalDealerHand = 0;
        // special rules, first drop the face cards to 11
        ArrayList<Integer> templist = dealerHandValList;
        for (Integer integer : templist) {
            if (integer >= 11) {
                integer = 10;
            }
            totalDealerHand = totalDealerHand + integer;
        }
        // check for aces if the new total is not above 11
        if (totalDealerHand <= 12) {
            for (Integer integerVal : dealerHandValList) {
                if (integerVal == 1 && totalDealerHand < 12) {
                    totalDealerHand = totalDealerHand + 10;
                }
            }
        }
        return totalDealerHand;
    }

    private int checkPlayerHand() {
        // this is to get the total number of the player's hand
        LinkedList<card> toExamine = playerHand.getCards();
        ArrayList<Integer> playerHandValList = new ArrayList<Integer>();
        for (card tempCard : toExamine) {
            playerHandValList.add(tempCard.getCardValNum());
        }
        int totalPlayerHand = 0;
        ArrayList<Integer> templist = playerHandValList;
        for (Integer integer : templist) {
            if (integer >= 11) {
                integer = 10;
            }
            totalPlayerHand = totalPlayerHand + integer;
        }
        // check for aces if the new total is not above 11
        if (totalPlayerHand < 12) {
            for (Integer integerVal : playerHandValList) {
                if (integerVal == 1 && totalPlayerHand < 12) {
                    totalPlayerHand = totalPlayerHand + 10;
                }
            }
        }
        return totalPlayerHand;
    }
}