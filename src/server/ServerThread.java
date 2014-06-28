package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import message.ChatMessage;
import message.Message;

public class ServerThread extends Thread{
	private ServerSocket socket;
	private Server server;
	private boolean stopped;
	
	public ServerThread(int port, Server server){
		try {
			this.socket = new ServerSocket(port);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		this.server = server;
		stopped = false;
	}
	
	@Override
	public void run(){
		listen();
	}
	
	public void listen(){
		while(!stopped){
			try {
				Socket soc = socket.accept();
				System.out.println(soc);
				server.addClient(soc);			
				server.sendAll(new ChatMessage("Server", "Hi"));
				
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}
	
	public void close(){
		stopped = true;
		interrupt();
	}
	

}
