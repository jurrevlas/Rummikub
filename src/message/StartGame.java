package message;

public class StartGame extends GameMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 125331273897836632L;

	public StartGame(String sender) {
		super(sender);
		message = "Game started!";
	}

}
