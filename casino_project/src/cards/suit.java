package cards;
public class suit {
	protected int suitID;
	public String suitName;

	public void setSuitName(String newString) {
		//this sets the suits name
		suitName = newString;
	};

	public int getSuitNo() {
		//this returns what the key is for the suit
		return suitID;
	};

	public String getSuitName() {
		// this returns the suit's name
		return suitName;
	}
}
