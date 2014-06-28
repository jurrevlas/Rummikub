package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import message.Message;

public class Client {
	
	protected Socket socket;
	protected Server server;
	private ObjectOutputStream out;
	private ClientThread listener;
	protected String clientName;
	
	public Client(Socket socket, Server server){
		clientName = "";
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {			
			e.printStackTrace();
		}
		this.server = server;
		listener = new ClientThread(this);
		listener.start();
	}
	
	public void sendMessage(Message message){
		try {
			out.writeObject(message);
		} catch (IOException e) {			
			//e.printStackTrace();
		}
	}
	
	public String getName(){
		return clientName;
	}
	
	@Override
	public String toString(){
		return clientName;
	}
	
	public void disconnect(){
		try {
			this.out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.server.removeClient(this);
	}
	
}
