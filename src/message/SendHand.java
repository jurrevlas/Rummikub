package message;

import game.Player;

public class SendHand extends GameMessage{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8159778088249068264L;
	
	private Player player;
	
	public SendHand(String sender, Player player) {
		super(sender);
		this.player = player;
		message = "Your hand is: " +this.getHand().toString();
	}
	
	public Player getHand(){
		return player;
	}

}
