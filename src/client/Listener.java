package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import message.Message;

public class Listener extends Thread {
	
	protected ObjectInputStream in;
	private Connection con;
	private boolean stopped;
	

	public Listener(Connection con){		
		this.con = con;		
		stopped = false;
	}
	
	
	public void run(){
		listen();
	}
	
	public void listen(){
		Object temp = null;

		try {			
			in = new ObjectInputStream(con.socket.getInputStream());		
		} catch (IOException e) {			
			e.printStackTrace();
		}

		while(!stopped){
			try {				
				temp = in.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			if(temp instanceof Message){
				
			}
		}
	}
	
	
	public void close(){
		stopped = true;
		interrupt();
	}
	

}
