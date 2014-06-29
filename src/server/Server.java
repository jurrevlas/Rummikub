package server;



import game.Game;
import game.Player;

import java.net.Socket;
import java.util.LinkedList;

import org.omg.CosNaming.IstringHelper;

import message.*;

public class Server extends Thread{	
	
	/** Accepts new clients*/
	private ServerThread serverSocket;
	/** List of clients*/
	private LinkedList<Client> clients;
	/** The Game*/
	protected Game game;
	
	public Server(int port){
		
		clients = new LinkedList<Client>();
		game = new Game(this);
		System.out.println("Server started on port... "+port);
		serverSocket = new ServerThread(port,this);	
		serverSocket.start();
	}
	
	public  void addClient(Socket socket){
		Client cli = new Client(socket,this);	
		if(!game.isFull()){
			cli.sendMessage(new Introduction("Server"));
			clients.add(cli);
		}else
			cli.sendMessage(new GameFull("Server"));
		System.out.println(clients.size());
		System.out.println(game.getPlayers().size());
	}
	
	public void removeClient(Client cli){
		System.out.println("rm client: " + cli.getPlayer().toString());
		System.out.println("rm player: " + clients.getLast().getPlayer().toString());
		game.removePlayer(cli.getPlayer());
		this.clients.remove(cli);
		System.out.println("[rP]Status: Cl: " + this.clients.size() + " Pl: " + this.game.getPlayers().size());
	}
	
	public void sendAll(Message message){
		for(Client cli : clients)
			cli.sendMessage(message);
	}
	
	public void send(String clientName, Message message){
		for(Client cli : clients)
			if(cli.getPlayer().getName().equals(clientName))
				cli.sendMessage(message);
	}
	
	public void handleMessage(Client c, Message message){
		System.out.println("Server/handleMessage/"+message+" "+message.getClass());
		if(message instanceof Introduction){
			Player player = new Player(message.getSender());
			c.setPlayer(player);
			System.out.println("add Player");
			this.game.addPlayer(player);
		}
		if(message instanceof ChatMessage){
			sendAll(message);
		}
		if(message instanceof GameMessage){
			System.out.println("server/handleMessage/GameMessage");
			game.handleGameMessage(message);
		}
		
		System.out.println("[hM]Status: Cl: " + this.clients.size() + " Pl: " + this.game.getPlayers().size());
	}
			
	public static void main(String[] args) throws InterruptedException{
		Server server = new Server(12345);
		while(true){
			sleep(1000);
		}
	}
	
	public int getPlayerCount(){
		return clients.size();
	}
}
