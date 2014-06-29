package game;

import java.io.Serializable;

public class Tile implements Serializable, Comparable<Tile>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1218539882365873060L;
	
	private Color color;
	private int number;
	private Boolean select = false;
	private boolean placedThisRound = true;
	
	public Tile(Color color, int number){
		this.color = color;
		this.number = number;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public int getNumber(){
		return this.number;
	}

	@Override
	public int compareTo(Tile tile) {
		if(color.equals(tile.color))
			return number - tile.number;
		else
			return color.compareTo(tile.color);
	}	
	
	@Override
	public String toString(){
		return color.toString()+number;
	}

	public void select(){
		this.select = true;
	}
	
	public void deSelet(){
		this.select = false;
	}
	public Boolean isSelected(){
		return select;
	}
}
