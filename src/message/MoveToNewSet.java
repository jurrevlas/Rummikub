package message;

import game.Set;
import game.Tile;

public class MoveToNewSet extends GameMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2587509429505721964L;
	private Tile tile;
	private Set source;
	private Set destination;
	
	public MoveToNewSet(String sender, Tile tile, Set source,Set destination) {
		super(sender);
		this.tile = tile;
		this.source = source;
		this.destination = destination;
		message = sender+ " moves " +tile.toString() +" from " +source.toString()+" to a new Set";
	}
	
	public Tile getTile(){
		return tile;
	}
	
	public Set getSource(){
		return source;
	}
	
	public Set getDestination(){
		return destination;
	}

}
