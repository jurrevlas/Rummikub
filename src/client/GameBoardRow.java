package client;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import game.Color;
import game.Set;
import game.Tile;

import javax.imageio.ImageIO;
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
//				
//				GameTile gt = GameBoard.getInstance().gametile;
//				GameBoardRow bgt = (GameBoardRow)gt.getParent();
//				if(gt != null){
//					gt.getTile().deSelet();
//					s.add(gt.getTile());
//					bgt.s.remove(gt.getTile());
//					if(GameBoard.getInstance().hand != bgt && bgt.s.isEmpty()){
//						bgt.getParent().remove(bgt);
//					}
//					GameBoard.getInstance().gametile = null;
//					gt.invalidate();
//					bgt.invalidate();
//					/*
//					Component a = gt;
//					while(a.getParent() != null){
//						a = a.getParent();
//						a.repaint();
//						a.validate();
//					}
//					a = GameBoard.getInstance().hand;
//					while(a.getParent() != null){
//						a.repaint();
//						a.validate();
//						a = a.getParent();
//						a.repaint();
//						a.validate();
//					}*/
//					Clientgui.getInstance().repaint();
//				}
				super.mouseClicked(e);
			}
		});
		setBackground(java.awt.Color.black);
		//this.add(Box.createRigidArea(new Dimension(60,86)));
	}
	
	private void addTiles(){
		if (s != null){
			for(Tile t : s){
				GameTile j = null;
				if (t.isSelected()){
					try {
						BufferedImage bimg = ImageIO.read(new File("src/images/"+t.toString()+".png"));
						for (int x = 0; x < bimg.getWidth(); x++) {
				            for (int y = 0; y < bimg.getHeight(); y++) {
				                int rgba = bimg.getRGB(x, y);
				                java.awt.Color col = new java.awt.Color(rgba, true);
				                col = new java.awt.Color(255 - col.getRed(),255 - col.getGreen(),255 - col.getBlue());
				                bimg.setRGB(x, y, col.getRGB());
				            }
				        }
						j = new GameTile(new ImageIcon("src/images/"+t.toString()+".png"),t);
						j.setIcon(new ImageIcon(bimg));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else{
					j = new GameTile(new ImageIcon("src/images/"+t.toString()+".png"),t);
				}
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
