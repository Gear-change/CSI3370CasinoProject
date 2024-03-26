package deckCards;
import cards.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    public String deckCommonName;
    public int cardAmt;
    public ArrayList<card> cardsInDeck = new ArrayList<card>();

    public Deck(int deckNum, List<String> cardNameList, ArrayList<Integer> cardVaList, ArrayList<String> cardSuitsNameList) {
        for (int i = 0; i < deckNum; i++) {
            int tempnum2 = 1;
            for (String tempSuit : cardSuitsNameList) {
                int tempNum = 1;

                for (int cardNum : cardVaList) {
                    cardsInDeck.add(new card(cardNum, tempnum2, tempSuit, cardNameList.get(tempNum - 1)));
                    tempNum = tempNum + 1;
                }
                tempnum2 = tempnum2 + 1;
            }
        }
    }



	public LinkedList<card> GetDeckList() {
        LinkedList<card> tempLL = new LinkedList<card>();
        tempLL.addAll(cardsInDeck);
        return tempLL;
    }
}
