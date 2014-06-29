package game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import server.Server;
import message.*;


public class Game extends Observable{
	
	private LinkedList<Player> players;
	private int currTurn;
	private int maxPlayers;	
		
	private Table table;	
	
	private boolean started;
	private boolean won;
	
	private Table backUpTable;
	private Player backUpPlayer;
	
	private Server server;
	
	
	public Game(Server server){
		this.server = server;
		//player options
		maxPlayers = 4;			
		players = new LinkedList<Player>();		
		
		//table options
		table = new Table();		
		
		//game options
		started = false;
		won = false;
	}
	
	public void removePlayer(Player player){
		for(Iterator<Player> iter = players.iterator(); iter.hasNext();) {
			Player p = iter.next();
		    if (p.getName() == player.getName()) {
		        iter.remove();
		    }
		}
	}
	
	//setter and getter
	public boolean addPlayer(Player player){
		if(players.size() >= maxPlayers && !started)
			return false;
		else
			players.add(player);
		
		return true;		
	}
	
	public LinkedList<Player> getPlayers(){
		return players;
	}		
	
	public boolean started(){
		return started;
	}
	
	//game methods
	
	public boolean startGame(){
		System.out.println("WOOT");
		//only starts once
		if(!started && players.size()>=2){
			
			System.out.println("we doint it");
			//give each player his hand
			for(Player p : players){
				for(int i = 0; i < 14; i++){
					p.add(table.popFromStack());
				}
				server.send(p.getName(), new SendHand("Server",p));
			}
			
			
			
			
			//roll for first turn			
			currTurn = (int) (100*Math.random() % players.size());
						
			//TODO notify player whose turn it is
			//notifyAll();
			//set status to started
			started = true;
		}	
		return started;
	}
	
	public boolean isFull(){
		return players.size() == maxPlayers;
	}
	
	public void handleGameMessage(Message move){
		System.out.println("there was a game message");
		if(move instanceof StartGame){
			System.out.println("Game/handleMessage/StartGame");
			if(startGame())
				server.sendAll(new StartGame("Server"));
			else
				server.send(move.getSender(), new ChatMessage("Server","Nope"));
		}		
		
		if(move instanceof NewSet){
			System.out.println("New Set");
			NewSet temp = (NewSet)(move);
			System.out.println("Game: "+started+" "+temp.getSender().equals( players.get(currTurn).getName())+" "+players.get(currTurn).contains(temp.getTile()));
			if(	!started || 
				!temp.getSender().equals( players.get(currTurn).getName() ) //||
				//!players.get(currTurn).contains(temp.getTile()))
				)
			{
				System.out.println("Wrong move");
				server.send(move.getSender(), new WrongTurn());			
			}else{
				table.newSet(temp.getTile());
				players.get(currTurn).remove(temp.getTile());
				System.out.println("Valid move");
				server.sendAll(move);
			}
		}		
		
		if(move instanceof AddToSet){
			AddToSet temp =(AddToSet)(move);
			if(!started || 
					!temp.getSender().equals( players.get(currTurn) ) ||
					!players.get(currTurn).contains(temp.getTile()))
			{
				server.send(move.getSender(), new WrongTurn());
			}else{
				table.addToSet(temp.getDestination(), temp.getTile());
			}
		}
		
		if(move instanceof MoveToSet){
			MoveToSet temp = (MoveToSet)(move);
			if(!started ||
				!temp.getSender().equals( players.get(currTurn) ) ||
				!table.removeFromSet(temp.getSource(), temp.getTile()))
			{
				server.send(move.getSender(), new WrongTurn());
			}else{
				table.addToSet(temp.getDestination(), temp.getTile());
			}
		}
		
		if(move instanceof EndTurn){
			int status = endTurn();
			if(-1 == status){
				server.sendAll( new ChatMessage("Server", "Table not consistent. Restored Backup."));
				server.sendAll( new SendTable("Server",table));
				server.send(players.get((currTurn-1)%players.size()).getName(),
							new SendHand("Server",players.get((currTurn-1)%players.size())));
				server.send(players.get(currTurn).getName(), new YourTurn());
			}else if(0 == status){
				server.sendAll(new GameWon("Server",players.get(currTurn)));
			}else{
				server.send(players.get(currTurn).getName(), new YourTurn());
			}
		}
		
		
		
		
	}
	
	public Table getTable(){
		return table;
	}
	  
	
	
	public int endTurn(){
		if(!table.isValid()){
			restoreBackUp();
			currTurn = (currTurn +1) % players.size();
			return -1;
		}else if(players.get(currTurn).size() == 0){
			won = true;
			return 0;
		}else{			
			currTurn = (currTurn +1) % players.size();
			backUp();
			return 1;
		}
		//notifyAll();
	}	

	private void backUp(){
		backUpTable = (Table) table.clone();
		backUpPlayer = (Player) players.get(currTurn).clone();
	}
	
	private void restoreBackUp(){
		table = backUpTable;
		players.set(currTurn, backUpPlayer);
	}	
			
	@Override
	public String toString(){
		String str = new String();
		str+= "Players:\n";
		for(Player player : players)
			str += player.toString() +"\n";
		str+="\nTable\n";
		str+=table.toString();
		return str;		
	}
	
	public static void main(String[] args ){
		
		
		
		
	}
	
	
}
