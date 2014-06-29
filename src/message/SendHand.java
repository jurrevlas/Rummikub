package message;

import game.Set;

public class SendHand extends GameMessage{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8159778088249068264L;
	
	private Set hand;
	
	public SendHand(String sender, Set hand) {
		super(sender);
		this.hand = hand;
		message = "Your hand is: " +this.getHand().toString();
	}
	
	public Set getHand(){
		return hand;
	}

}
