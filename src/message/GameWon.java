package message;

import game.Player;

public class GameWon extends GameMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6131298810354603358L;
	
	private Player winner;

	public GameWon(String sender, Player winner) {
		super(sender);
		this.winner = winner;
	}
	
	public Player getWinner(){
		return winner;
	}

}
