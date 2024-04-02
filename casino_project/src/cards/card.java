package cards;
public class card extends suit {
    public int cardValNum;
    public String cardValNameString;

    public card(int cardno, int suitno, String suitstr, String cardvalname) {
        cardValNum = cardno;
        suitID = suitno;
        suitName = suitstr;
        cardValNameString = cardvalname;
    }

    public card() {
    	//this is for facedown cards
        cardValNameString = "none";
        cardValNum = 0;
        suitID = 0;
        suitName = "BackOfCard";
    }

	public int getCardValNum() {
		//this is the cards number for face cards, jack = 11, queen 12, and king is 13
        return cardValNum;
    }

    public String getCardValNameString() {
    	//this is for the name for that value
        return cardValNameString;
    }

    public String getCardFullString() {
    	//this is to get it as a string, mainly for text deployment
        if (cardValNum != 0) {
            return (cardValNameString + " of " + suitName);
        } else {
            return "a face-down card";
        }
    }

	public Integer getCardValNum1() {
		//this is an alternate for Integer instead of int
        return cardValNum;
	}

}
