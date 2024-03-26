import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    public String deckCommonName;
    public int cardAmt;
    public ArrayList<card> cardsInDeck = new ArrayList<card>();

    public Deck(int deckNum, List<?> cardNameList, List<?> cardVaList, List<?> cardSuitsNameList) {
        for (int i = 0; i < deckNum; i++) {
            int tempnum2 = 1;
            for (Object tempSuit : cardSuitsNameList) {
                int tempNum = 1;

                for (Object cardNum : cardVaList) {
                    cardsInDeck.add(card(cardNum, tempnum2, tempSuit, cardNameList.get(tempNum - 1)));
                    tempNum = tempNum + 1;
                }
                tempnum2 = tempnum2 + 1;
            }
        }
    }

    private card card(Object cardNum, int tempnum2, Object tempSuit, Object object) {
    	return card(cardNum, tempnum2, tempSuit, object);
	}

	public LinkedList<card> GetDeckList() {
        LinkedList<card> tempLL = new LinkedList<card>();
        tempLL.addAll(cardsInDeck);
        return tempLL;
    }
}