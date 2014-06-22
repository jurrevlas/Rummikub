package client;

import java.io.IOException;
import java.io.ObjectInputStream;

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
		Object temp;
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
			//TODO handle read objects
		}
	}
	
	
	public void close(){
		stopped = true;
		interrupt();
	}
	

}
