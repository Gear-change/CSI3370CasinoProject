import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

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

    public card drawFromContainer() {
        cardsVisable.removeFirst();
        return theContainer.pop();
    }

    public card checkCardIfVisable(int cardlocation) {
        if (cardsVisable.get(cardlocation)) {
            return theContainer.get(cardlocation);
        } else {
            return card();
        }
    }

    private card card() {
		return card();
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
}
