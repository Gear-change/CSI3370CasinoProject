import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    public String deckCommonName;
    public int cardAmt;
    public ArrayList<card> cardsInDeck = new ArrayList<card>();

    public Deck(int deckNum, List cardNameList, List cardVaList, List cardSuitsNameList) {
        for (int i = 0; i < Decknum; i++) {
            int tempnum2 = 1;
            for (Object tempSuit : cardSuits) {
                int tempNum = 1;

                for (Object cardNum : cardVaList) {
                    cardsInDeck.add(card(cardNum, tempNum2, tempsuit, cardtypes.get(tempNum - 1)));
                    tempNum = tempNum + 1;
                }
                tempnum2 = tempnum2 + 1;
            }
        }
    }

    public LinkedList GetDeckList() {
        LinkedList<card> tempLL = new LinkedList<card>();
        tempLL.addAll(cardsInDeck);
        return templl;
    }
}
