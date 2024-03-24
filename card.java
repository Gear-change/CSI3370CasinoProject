public class card extends suit {
    public int cardValNum;
    public String cardValNameString;

    public card(int cardno, int suitno, String suitstr, String cardvalname) {
        cardValNum = cardno;
        suitID = suitno;
        suitName = suitStr;
        cardValNameString = cardvalname;
    }

    public card() {
        cardValNameString = "none";
        cardValNum = 0;
        suitID = 0;
        suitName = "BackOfCard";
    }

    public int getCardValNum() {
        return cardValNum;
    }

    public String getCardValNameString() {
        return cardValNameString;
    }

    public String getCardFullString() {
        if (cardValNum != 0) {
            return (cardValNameString + " of " + suitName);
        } else {
            return "a face-down card";
        }
    }

}
