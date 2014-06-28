package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.BindException;

import message.*;


public class ClientThread extends Thread {
	
	
	private ObjectInputStream in;
	private boolean stopped;
	private Client client;
	
	public ClientThread(Client client){
		this.client = client;
		try {
			in = new ObjectInputStream(client.socket.getInputStream());
		} catch ( IOException e) {		
			if(e instanceof EOFException){
				System.out.println("Im off!");
				this.close();
			}
			else if(e instanceof BindException){
				System.out.println("Allready running");
			}
			else 
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
			if(temp instanceof Introduction)				
					client.clientName = ((Introduction)temp).getSender();
				else
					client.server.handleMessage((Message) temp);
			
		}
	}
	
	public void close(){
		stopped = true;
		this.interrupt();
	}
	

}
