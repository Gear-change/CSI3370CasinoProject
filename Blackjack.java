import java.util.ArrayList;
import java.util.List;
import com.example.*;

public class Blackjack {
    public Boolean gameEnded;
    public Deck thisDeck;
    private cardContainer toDeal;
    private cardContainer dealerHand;
    private cardContainer playerHand;
    private cardcontainer discard;
    public String stateOfPlay;

    Public Blackjack(int deckNum) {
        // first we need to make a list of the cards in the deck to feed to deck.java
        List<String> cardNameList = new List<String>();
        List<Integer> cardVaList = new List<Integer>();
        List<String> cardSuitsNameList = new List<String>();
        String[] cardset = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
                "Queen", "King" };
        String[] cardSuitsNames = { "Spades", "Hearts", "Clubs", "Diamonds" };
        Integer[] cardVals = new Integer[13];
        for (int i = 1; i <= cardVals.length; i++) {
            cardVals[i - 1] = i;
        }
        cardNameList.addAll(cardset);
        cardVaList.addAll(cardVals);
        cardSuitsNameList.addAll(cardSuitsNames);
        deckToUse = Deck(deckNum, cardNameList, cardVaList, cardSuitsNameList);
        // then we set the games state
        gameEnded = false;
        // then we make a shuffled deck
        toDeal = new cardContainer.CardContainerShuffled(thisDeck, true);
        stateOfPlay = "Start";
    };

    public Boolean gamePlayingBoolian() {
        return !gameEnded;
    }

    public void firstTurnDeal() {
        if (todeal.getContainerSize > 4) {
            playerHand.addCardToContainer(todeal.drawFromContainer, true);
            dealerHand.addCardToContainer(todeal.drawFromContainer, true);
            playerHand.addCardToContainer(todeal.drawFromContainer, true);
            dealerHand.addCardToContainer(todeal.drawFromContainer, false);
        } else {
            toDeal = new cardContainer.CardContainerShuffled(thisDeck, true);
            playerHand.addCardToContainer(todeal.drawFromContainer, true);
            dealerHand.addCardToContainer(todeal.drawFromContainer, true);
            playerHand.addCardToContainer(todeal.drawFromContainer, true);
            dealerHand.addCardToContainer(todeal.drawFromContainer, false);
        }
        int resultInt = this.checkDealerHand();
        int playerHandInit = this.checkPlayerHand();
        int dealerKnownCard = dealerHand.getCards(0);

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
        if (todeal.getContainerSize > 1) {
        } else {
            toDeal = new cardContainer.CardContainerShuffled(thisDeck, true);
        }
        playerTotal = this.checkPlayerHand();
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
        List currentDealCardList = new list();
        for (int i = 0; i < dealerHand.getContainerSize; i++) {
            currentDealCardList.add(dealerHand.checkCardIfVisable(i));
        }
        toReturn.concat("The dealer's hand contains: ");
        for (card thiscard : currentDealCardList) {
            toReturn.concat(thiscard.getCardFullString() + " ");
        }
        List currentPlayerCardList = new List() {
        };
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
        LinkedList toExamine = dealerHand.getCards();
        ArrayList<Integer> dealerHandValList = new ArrayList<Integer>();
        for (card tempCard : toExamine) {
            dealerHandValList.add(tempCard.getCardVal());
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
        LinkedList toExamine = playerHand.getcards();
        ArrayList<Integer> playerHandValList = new ArrayList<Integer>();
        for (card tempCard : toExamine) {
            playerHandValList.add(tempCard.getCardVal());
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