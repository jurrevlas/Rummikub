package message;

import game.Set;
import game.Tile;

public class MoveToHand extends GameMessage {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3678116430094117994L;
	
	private Tile tile;
	
	private Set from;
	
	public MoveToHand(String sender,Tile tile, Set from) {
		super(sender);
		this.tile = tile;
		this.from = from;
	}
	
	public Set getSource(){
		return from;
	}
	
	public Tile getTile(){
		return tile;
	}

}
