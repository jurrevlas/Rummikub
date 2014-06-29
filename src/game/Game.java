package game;

import java.util.LinkedList;
import java.util.Observable;

public class Game extends Observable{
	
	private LinkedList<Player> players;
	private int currTurn;
	private int maxPlayers;	
		
	private Table table;	
	
	private boolean started;
	private boolean won;
	
	private Table backUpTable;
	private Player backUpPlayer;
	
	
	public Game(){
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
		for(Player p: players){
			if(p.getName() == player.getName()){
				System.out.println("Premove: " + p.getName());
				players.remove(p);
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
	
	//game methods
	
	public boolean startGame(){
		
		//only starts once
		if(!started && players.size()>=2){
			
			
			//give each player his hand
			for(Player p : players){
				for(int i = 0; i < 14; i++){
					p.add(table.popFromStack());
				}
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
	
	public void move(){
		//TODO
	}
	
	public Table getTable(){
		return table;
	}
	
	
	
	public void endTurn(){
		if(!table.isValid()){
			restoreBackUp();
		}else if(players.get(currTurn).size() == 0){
			won = true;			
		}else{			
			currTurn = (currTurn +1) % players.size();
			backUp();
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
