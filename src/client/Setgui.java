package client;

import java.awt.Dimension;
import java.awt.Frame;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Color;
import game.Player;
import game.Set;
import game.Tile;

public class Setgui extends JPanel {

	private Set set;
	private Hashtable<String, ImageIcon> tiles; 
	
	/**
	 * Create the panel.
	 */
	public Setgui(Set set) {
		//panel options
		
		setLayout(null);
		
		//init attributes
		this.set = set;
		tiles = new Hashtable<String, ImageIcon>();
		for(game.Color c : game.Color.values()){
			for(int i = 1; i<=13; i++){
				tiles.put(c.toString()+i, new ImageIcon("src/images/"+c.toString()+i+".png"));
			}
		}
		
		repaint();
		
		
		
	}
	
	@Override
	public void repaint(){
		this.setPreferredSize(new Dimension(70*set.size(),90));
		
		int pos = 5;
		
		
		for(Tile t : set){
			JLabel temp = new JLabel(tiles.get(t.toString()));
			temp.setBounds(pos, 2, 60, 86);
			pos += 65;
		}		
		super.repaint();
	}	
	
}
