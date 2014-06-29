package client;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Color;
import game.Set;
import game.Tile;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

@SuppressWarnings("serial")
public class GameBoardRow extends JPanel{
	public Set s;
	public GameBoardRow(Set tiles){
		this.s = tiles;
		this.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicked on the gameboardrow");
				super.mouseClicked(e);
			}
		});
		setBackground(java.awt.Color.black);
		//this.add(Box.createRigidArea(new Dimension(60,86)));
	}
	
	private void addTiles(){
		if (s != null){
			for(Tile t : s){
				GameTile j = new GameTile(new ImageIcon("src/images/"+t.toString()+".png"),t);
				this.add(j);
			}
		}
	}
	
	@Override
	public void repaint() {
		this.removeAll();
		this.addTiles();
		Component a = this;
		while(a.getParent() != null){
			a = a.getParent();
			a.repaint();
			a.validate();
		}
	}
}
