package message;

import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6596264275448655301L;
	
	private String sender;	
	
	public Message(String sender){
		this.sender = sender;
		
	}
	
	public String getSender(){
		return this.sender;
	}
	
	

	
	
	
	
	
}
