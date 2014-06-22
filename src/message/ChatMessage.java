package message;

public class ChatMessage extends Message {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9217708005274067135L;
	private String message;
	
	public ChatMessage(String sender , String message) {
		super(sender,MessageType.ChatMessage);
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}

}
