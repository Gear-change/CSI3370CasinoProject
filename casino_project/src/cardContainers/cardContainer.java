package cardContainers;
import java.util.ArrayList;
import deckCards.Deck;
import java.util.LinkedList;
import java.util.Random;
import cards.*;
public class cardContainer {
    public LinkedList<card> theContainer = new LinkedList<card>();
    public ArrayList<Boolean> cardsVisable = new ArrayList<Boolean>();

    public cardContainer() {};

    public cardContainer(Deck toShuffleDeck, boolean faceDown){
        LinkedList<card> tempLL = toShuffleDeck.GetDeckList();
        Random rand = new Random();
        while(!tempLL.isEmpty()) {
            theContainer.add((card) tempLL.remove(rand.nextInt(tempLL.size())));
            cardsVisable.add(!faceDown);
        }
    }
    public card getCardFromIndex(int index) {
		return theContainer.get(index);
    	
    }
    public void setVisability(int index, boolean toSet) {
    	cardsVisable.set(index, toSet);
    }

    public card drawFromContainer() {
        cardsVisable.removeFirst();
        return theContainer.pop();
    }

    public card checkCardIfVisable(int cardlocation) {
        if (cardsVisable.get(cardlocation)) {
            return theContainer.get(cardlocation);
        } else {
        	card thisCard = new card();
            return thisCard;
        }
    }

    public void clearContainer() {
    	theContainer.clear();
    	cardsVisable.clear();
    }

	public void addCardToContainer(card toAdd, boolean IsVisable) {
        theContainer.add(toAdd);
        cardsVisable.add(IsVisable);
    }

    public LinkedList<card> getCards() {
        return theContainer;
    }

    public int getContainerSize() {
        return theContainer.size();
    }

    public boolean getVisability(int index) {
        return cardsVisable.get(index);
    }

    public int getCardValAt(int index) {
        return theContainer.get(index).getCardValNum();
    }
    public void addCardsFromContainer(cardContainer toAdd, boolean facedown) {
    	LinkedList<card> tempLL = toAdd.getCards();
    	Random rand = new Random();
        while(!tempLL.isEmpty()) {
            theContainer.add((card) tempLL.remove(rand.nextInt(tempLL.size())));
            cardsVisable.add(!facedown);
        }
        return;
    }
}
