package message;

import game.Set;

public class SendHand extends GameMessage{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8159778088249068264L;
	
	private Set set;
	
	public SendHand(String sender, Set hand) {
		super(sender);
		set = hand;
		message = "Your hand is: " +set.toString();
	}
	
	public Set getHand(){
		return set;
	}

}
