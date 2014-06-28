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
		for(Tile t : s){
			JLabel j = new JLabel(new ImageIcon("src/images/"+t.toString()+".png"));
			j.addMouseListener(new MouseInputAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					s.add(new Tile(Color.Yellow, 3));
					System.out.println(e);
					super.mouseClicked(e);
				}
			});
			this.add(j);
		}
		//this.add(Box.createRigidArea(new Dimension(60,86)));
		//JLabel whatever = new JLabel(tiles.get("Blk2"));
		//this.add(whatever);
		//this.add(new JLabel(tiles.get("Blk2")));
	}
	
	@Override
	public void repaint() {
		this.removeAll();
		
		if (s != null){
			for(Tile t : this.s){
				JLabel j = new JLabel(new ImageIcon("src/images/"+t.toString()+".png"));
				/*j.addMouseListener(new MouseInputAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						s.add(new Tile(Color.Yellow, 3));
						Component a = (Component)e.getSource();
						while(a.getParent() != null){
							a = a.getParent();
							a.repaint();
							a.validate();
						}
						super.mouseClicked(e);
					}
				});*/
				this.add(j);
			}
		}
		for(Component c : this.getComponents()){
			c.repaint();
			c.validate();
		}
		Component a = this;
		while(a.getParent() != null){
			a = a.getParent();
			a.repaint();
			a.validate();
		}
	}
}
