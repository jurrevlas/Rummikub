package message;

import game.Set;
import game.Tile;

public class MoveToSet extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2276459890233172247L;
	private Tile tile;
	private Set from;
	private Set to;

	public MoveToSet(String sender, Tile tile, Set from, Set to) {
		super(sender);
		this.tile = tile;
		this.from = from;
		this.to = to;
	}

}