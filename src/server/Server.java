package server;

import game.Game;

import java.net.Socket;
import java.util.LinkedList;

import message.Message;
import message.MessageType;


public class Server extends Thread{	
	
	/** Accepts new clients*/
	private ServerThread serverSocket;
	/** List of clients*/
	private LinkedList<Client> clients;
	/** The Game*/
	private Game game;
	
	
	
	public Server(int port){
		
		serverSocket = new ServerThread(port,this);		
		serverSocket.start();
		clients = new LinkedList<Client>();
		game = new Game();		
		System.out.println("Server started on port... "+port);
	}
	
	public void addClient(Socket socket){
		Client cli = new Client(socket,this);
		
<<<<<<< HEAD
		
=======
	}
	
	public void run(){
		int i =0;
		while(true){
			
		}
>>>>>>> ea5f5500d1f6aa57621f267bedb3eb9d87709297
		
	}
		
	public void sendAll(Message message){
		for(Client cli : clients)
			cli.sendMessage(message);
	}
	
	public void send(String clientName, Message message){
		for(Client cli : clients)
			if(cli.getName().equals(clientName))
				cli.sendMessage(message);
	}
	
	public void handleMessage(Message message){
		
	}
	
		
	

}
