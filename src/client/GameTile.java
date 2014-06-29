package client;

import game.Tile;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class GameTile extends JLabel{
	private Tile t;
	private Boolean select;
	public GameTile(ImageIcon i, Tile t){
		super(i);
		setTile(t);
	}
	
	public void setTile(Tile t){
		this.t = t;
	}
	
	public Tile getTile(){
		return t;
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
