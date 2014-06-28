package message;

import game.Tile;

public class NewSet extends Message{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 387809702866028586L;
	private Tile tile;
	
	public NewSet(String sender, Tile tile) {
		super(sender);
		this.tile = tile;
	}
	
	public Tile getTile(){
		return tile;
	}
	

}
