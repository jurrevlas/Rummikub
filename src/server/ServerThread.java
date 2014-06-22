package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
				int i = 0;
				System.out.println(++i);
				server.addClient(soc);
				
				
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
