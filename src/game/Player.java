package game;


import java.util.Observable;
import java.util.Observer;

public class Player extends Set implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4786935800706120868L;
	
	private String name;
	
	public Player(String name){
		this.name = name;					
	}
		
	public String getName(){
		return name;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}	
	
	@Override
	public String toString(){
		return name +"\n" +super.toString();
		
	}
}
