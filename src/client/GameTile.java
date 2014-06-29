package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import game.Set;
import game.Tile;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

import message.AddToSet;
import message.MoveToSet;

@SuppressWarnings("serial")
public class GameTile extends JLabel{
	private Tile t;

	private ImageIcon selected;
	private ImageIcon unselected;
	
	public GameTile(Tile tile){
		super(new ImageIcon("src/images/"+tile.toString()+".png"));
		setTile(tile);
		try {
			BufferedImage bimg = ImageIO.read(new File("src/images/"+getTile().toString()+".png"));
			for (int x = 0; x < bimg.getWidth(); x++) {
	            for (int y = 0; y < bimg.getHeight(); y++) {
	                int rgba = bimg.getRGB(x, y);
	                Color col = new Color(rgba, true);
	                col = new Color(255 - col.getRed(),255 - col.getGreen(),255 - col.getBlue());
	                bimg.setRGB(x, y, col.getRGB());
	            }
	        }
			selected = new ImageIcon(bimg);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		unselected = new ImageIcon("src/images/"+tile.toString()+".png");
		
		addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameBoard gb = GameBoard.getInstance();
				GameTile gt = (GameTile)e.getSource();
				if(gb.gametile != null){
					if(gb.hand == gb.gametile.getParent() && gb.hand != ((GameTile)e.getSource()).getParent()){
						gb.gametile.getTile().deSelet();
						Clientgui.getInstance().sendMessage(new AddToSet(Clientgui.getInstance().playerName,
															gb.gametile.getTile(), 
															((GameBoardRow)(gt).getParent()).s));
					}
					if(gb.gametile == gt){
						gt.getTile().deSelet();
						gb.gametile = null;
					}
					if(gb.gametile != gt && gb.hand != gb.gametile.getParent()){
						gb.gametile.getTile().deSelet();
						Clientgui.getInstance().sendMessage(new MoveToSet(Clientgui.getInstance().playerName, gb.gametile.getTile(),
								((GameBoardRow)(gb.gametile.getParent())).s, ((GameBoardRow)(gt).getParent()).s));
					}
				}else{
					gt.getTile().select();
					gb.gametile = gt;
				}
				gt.validate();
				gt.repaint();
				gb.validate();
				gb.repaint();
//				GameTile gt = (GameTile)e.getSource();
//				GameTile bgt = GameBoard.getInstance().gametile;
//				if(bgt != null && bgt != gt){
//					bgt.getTile().deSelet();
//					((GameBoardRow)bgt.getParent()).s.remove(bgt.getTile());
//					if(GameBoard.getInstance().hand != ((GameBoardRow)bgt.getParent())){
//						((GameBoardRow)bgt.getParent()).getParent().remove(((GameBoardRow)bgt.getParent()));
//					}
//					((GameBoardRow)gt.getParent()).s.add(GameBoard.getInstance().gametile.getTile());
//					GameBoard.getInstance().gametile = null;
//					gt.invalidate();
//					bgt.invalidate();
//					//repaint
//					Clientgui.getInstance().repaint();
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
//					//repaint
//					return;
//				}
//				if (gt.getTile().isSelected() && GameBoard.getInstance().gametile == gt){
//					gt.getTile().deSelet();
//					gt.setIcon(new ImageIcon("src/images/"+gt.getTile().toString()+".png"));
//					System.out.println("GT: "+gt.getTile()+" "+gt.getTile().isSelected());
//					GameBoard.getInstance().gametile = null;
//					gt.invalidate();
//				}else{
//					if (GameBoard.getInstance().gametile != null){
//						return;
//					}
//					Set set = ((GameBoardRow)gt.getParent()).s;
//					boolean oneSelected = false;
//					for (Tile t : set){
//						if (t.isSelected()){
//							oneSelected = true;
//						}
//						GameBoard.getInstance().gametile = null;
//					}
//					if (!oneSelected){
//						gt.getTile().select();
//						//LOL image stuff starts here
//						try {
//							BufferedImage bimg = ImageIO.read(new File("src/images/"+gt.getTile().toString()+".png"));
//							for (int x = 0; x < bimg.getWidth(); x++) {
//					            for (int y = 0; y < bimg.getHeight(); y++) {
//					                int rgba = bimg.getRGB(x, y);
//					                Color col = new Color(rgba, true);
//					                col = new Color(255 - col.getRed(),255 - col.getGreen(),255 - col.getBlue());
//					                bimg.setRGB(x, y, col.getRGB());
//					            }
//					        }
//							gt.setIcon(new ImageIcon(bimg));
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
//						GameBoard.getInstance().gametile = gt;
//						//LOL image stuff ends here
//						gt.invalidate();
//					}
//					Clientgui.getInstance().repaint();
//					System.out.println("GT: "+gt.getTile()+" "+gt.getTile().isSelected());
//				}
//				Clientgui.getInstance().repaint();
				
				
				super.mouseClicked(e);
			}
		});
	}
	
	public void setTile(Tile t){
		this.t = t;
	}
	
	public Tile getTile(){
		return t;
	}
	
	@Override
	public void repaint() {
		if(getTile() != null && getTile().isSelected()){
			setIcon(selected);
		}else{
			setIcon(unselected);
		}
		super.repaint();
	}
}
