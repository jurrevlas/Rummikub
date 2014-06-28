package message;

import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6596264275448655301L;
	
	private String sender;
	private MessageType type;
	
	public Message(String sender, MessageType type){
		this.sender = sender;
		this.type = type;
	}
	
	public String getSender(){
		return this.sender;
	}
	
	public MessageType getType(){
		return type;
	}

	
	
	
	
	
}
