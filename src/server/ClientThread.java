package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import message.Message;
import message.MessageType;

public class ClientThread extends Thread {
	
	
	private ObjectInputStream in;
	private boolean stopped;
	private Client client;
	
	public ClientThread(Client client){
		this.client = client;
		try {
			in = new ObjectInputStream(client.socket.getInputStream());
		} catch (IOException e) {			
			e.printStackTrace();
		}
		stopped = false;
	}
	
	public void run(){
		listen();
	}
	
	public void listen(){
		Object temp = null;
		while(!stopped){			
			try {
				temp = in.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			if(temp instanceof Message)
				if(((Message)temp).getType() == MessageType.Introduction)
					client.clientName = ((Message)temp).getSender();
				else
					client.server.handleMessage((Message) temp);
			
		}
	}
	
	public void close(){
		stopped = true;
		this.interrupt();
	}
	

}
