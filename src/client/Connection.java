package client;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.ObjectOutputStream;

import message.Message;


public class Connection extends Thread{
	
	protected Socket socket;	
	protected Clientgui client;
	private Listener listener;
	private ObjectOutputStream out;
	
	
	
	public Connection(String ip, int port, Clientgui client) throws IOException{
		socket = new Socket(ip,port);
		this.client = client;
		out = new ObjectOutputStream(socket.getOutputStream());		
		listener = new Listener(this);		
		listener.start();
	}
	
	public void sendMessage(Message message){
		try {
			out.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
