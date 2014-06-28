package server;

import game.Game;

import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import message.ChatMessage;
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
		
		clients = new LinkedList<Client>();
		game = new Game();
		System.out.println("Server started on port... "+port);
		serverSocket = new ServerThread(port,this);	
		serverSocket.start();
	}
	
	public  void addClient(Socket socket){
		Client cli = new Client(socket,this);		
		if(!game.isFull()){
			cli.sendMessage(new Message("Server",MessageType.Introduction));
			clients.add(cli);
		}else
			cli.sendMessage(new Message("Server",MessageType.GameFull));
		System.out.println(clients.size());
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
		switch(message.getType()){
		
		}
	}
	
		
	public static void main(String[] args) throws InterruptedException{
		Server server = new Server(12345);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		while(true){
			Date date = new Date();
			server.sendAll(new ChatMessage("Time",dateFormat.format(date)));
			sleep(1000);
		}
	}

}
