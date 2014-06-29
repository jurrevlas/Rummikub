package message;

import game.Tile;
import game.Set;

public class NewSet extends GameMessage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 387809702866028586L;
	private Tile tile;
	private Set set;
	
	public NewSet(String sender, Tile tile) {
		super(sender);
		this.tile = tile;
		set = null;
	}
	public NewSet(String sender,Set set){
		super(sender);
		this.set = set;
		this.tile = set.getFirst(); 
	}
	
	public Tile getTile(){
		return tile;
	}
	
	public Set getSet(){
		return set;
	}
	

}
