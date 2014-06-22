package client;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.ObjectOutputStream;

import message.Message;

public class Connection {
	
	protected Socket socket;	
	protected Clientgui client;
	private Listener listener;
	private ObjectOutputStream out;
	
	
	
	public Connection(String ip, int port) throws IOException{
		socket = new Socket(ip,port);
		System.out.println(socket.toString());
		out = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("test3");
		listener = new Listener(this);
		
		System.out.println("test");
		
	}
	
	public void sendMessage(Message message){
		try {
			out.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
