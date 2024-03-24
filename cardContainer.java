import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import com.example.card;

public class cardContainer {
    public LinkedList<card> theContainer = new LinkedList<card>();
    public ArrayList<Boolean> cardsVisable = new ArrayList<Boolean>();

    public CardContainer() {};

    public CardContainerShuffled(Deck toShuffleDeck, boolean faceDown){
        LinkedList tempLL = toShuffleDeck.GetDeckList();
        Random rand = new Random();
        while(tempLL.length()!= 0) {
            theContainer.add(tempLL.pop());
            cardsVisable.add(!faceDown);
        }
    }

    public Card drawFromContainer() {
        cardsVisable.removeFirst();
        return theContainer.pop();
    }

    public Card checkCardIfVisable(int cardlocation) {
        if (cardsVisable.get(cardlocation)) {
            return theContainer.get(cardlocation);
        } else {
            return card();
        }
    }

    public void addCardToContainer(card toAdd, boolean IsVisable) {
        theContainer.add(toadd);
        cardsVisable.add(IsVisable);
    }

    public LinkedList getCards() {
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
