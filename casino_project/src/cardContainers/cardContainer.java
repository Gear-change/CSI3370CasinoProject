package cardContainers;
import java.util.ArrayList;
import deckCards.Deck;
import java.util.LinkedList;
import java.util.Random;
import cards.*;
public class cardContainer {
	//this is the cards themselves
    public LinkedList<card> theContainer = new LinkedList<card>();
    //this is to see if the player can view them
    public ArrayList<Boolean> cardsVisable = new ArrayList<Boolean>();

    public cardContainer() {};

    public cardContainer(Deck toShuffleDeck, boolean faceDown){
    	//this adds the cards from the deck in a random order to the container, useful for initiating a new set of decks
        LinkedList<card> tempLL = toShuffleDeck.GetDeckList();
        Random rand = new Random();
        while(!tempLL.isEmpty()) {
            theContainer.add((card) tempLL.remove(rand.nextInt(tempLL.size())));
            cardsVisable.add(!faceDown);
        }
    }
    public card getCardFromIndex(int index) {
    	//this returns the card at the index as the card class
		return theContainer.get(index);
    	
    }
    public void setVisability(int index, boolean toSet) {
    	//this is for revealing cards and hiding them after they are added, not during addition
    	cardsVisable.set(index, toSet);
    }

    public card drawFromContainer() {
    	//this is to draw from, to use as a deck
        cardsVisable.removeFirst();
        return theContainer.pop();
    }

    public card checkCardIfVisable(int cardlocation) {
    	//this checks the card visability and returns if it is visable or not
        if (cardsVisable.get(cardlocation)) {
            return theContainer.get(cardlocation);
        } else {
        	card thisCard = new card();
            return thisCard;
        }
    }

    public void clearContainer() {
    	//this is for removing everything from the container
    	theContainer.clear();
    	cardsVisable.clear();
    }

	public void addCardToContainer(card toAdd, boolean IsVisable) {
		//this adds the card, and sets visibility
        theContainer.add(toAdd);
        cardsVisable.add(IsVisable);
    }

    public LinkedList<card> getCards() {
    	//this returns the cards as a linked list of cards
        return theContainer;
    }

    public int getContainerSize() {
    	//this returns the number of cards in the deck
        return theContainer.size();
    }

    public boolean getVisability(int index) {
    	//this checks the visability
        return cardsVisable.get(index);
    }

    public int getCardValAt(int index) {
    	//this returns the value of the card at a index
        return theContainer.get(index).getCardValNum();
    }
    public void addCardsFromContainer(cardContainer toAdd, boolean facedown) {
    	//this adds cards from the given cardcontainer, and shuffles them in to this one
    	LinkedList<card> tempLL = toAdd.getCards();
    	Random rand = new Random();
        while(!tempLL.isEmpty()) {
            theContainer.add((card) tempLL.remove(rand.nextInt(tempLL.size())));
            cardsVisable.add(!facedown);
        }
        return;
    }
}
