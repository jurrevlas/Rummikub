package message;

public class YourTurn extends GameMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7603732953252855746L;

	public YourTurn() {
		super("Server");
		message = "It's your turn!";
	}

}
