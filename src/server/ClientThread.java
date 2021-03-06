package server;


import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.BindException;

import game.*;
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
				//System.out.println("Im off!");
				try {
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(e instanceof BindException){
				//System.out.println("Allready running");
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
				//e.printStackTrace();
				try {
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}
			if(temp instanceof Message){
				client.server.handleMessage(this.client, (Message) temp);
			}
			
		}
	}
	
	public void close() throws IOException{
		stopped = true;
		this.interrupt();
		client.server.game.removePlayer(this.client.getPlayer());
		this.in.close();
		this.client.disconnect();
		
	}
	

}
