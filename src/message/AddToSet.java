package message;

import game.Set;
import game.Tile;

public class AddToSet extends GameMessage {



	/**
	 * 
	 */
	private static final long serialVersionUID = -8600010341800297131L;
	private Tile tile;
	private Set to;
	
	public AddToSet(String sender, Tile tile, Set to) {
		super(sender);
		this.tile = tile;
		this.to = to;
		message = sender+" adds to Set: " +to.toString() +" the tile "+tile.toString();
		
	}
	
	public Tile getTile(){
		return tile;
	}
	
	public Set getDestination(){
		return to;
	}

}
