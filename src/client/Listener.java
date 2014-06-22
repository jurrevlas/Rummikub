package client;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Listener extends Thread {
	
	protected ObjectInputStream in;
	private Connection con;
	private boolean stopped;
	
	public Listener(Connection con){
		System.out.println("recursive?");
		this.con = con;
		System.out.println("why?");
		stopped = false;
		System.out.println(con.socket.toString());
		
	}
	
	
	public void run(){
		listen();
	}
	
	public void listen(){
		Object temp;
		
		try {			
			in = new ObjectInputStream(con.socket.getInputStream());
			System.out.println("why?");
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
