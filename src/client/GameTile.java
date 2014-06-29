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

import game.Set;
import game.Tile;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

@SuppressWarnings("serial")
public class GameTile extends JLabel{
	private Tile t;

	public GameTile(ImageIcon i, Tile tile){
		super(i);
		setTile(tile);
		addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameTile gt = (GameTile)e.getSource();
				if(GameBoard.getInstance().gametile != null && GameBoard.getInstance().gametile != gt){
					GameBoard.getInstance().gametile.getTile().deSelet();
					((GameBoardRow)GameBoard.getInstance().gametile.getParent()).s.remove(GameBoard.getInstance().gametile.getTile());
					if(GameBoard.getInstance().hand != ((GameBoardRow)GameBoard.getInstance().gametile.getParent())){
						((GameBoardRow)GameBoard.getInstance().gametile.getParent()).getParent().remove(((GameBoardRow)GameBoard.getInstance().gametile.getParent()));
					}
					((GameBoardRow)gt.getParent()).s.add(GameBoard.getInstance().gametile.getTile());
					GameBoard.getInstance().gametile = null;
					//repaint
					Component a = gt;
					while(a.getParent() != null){
						a = a.getParent();
						a.repaint();
						a.validate();
					}
					a = GameBoard.getInstance().hand;
					while(a.getParent() != null){
						a.repaint();
						a.validate();
						a = a.getParent();
						a.repaint();
						a.validate();
					}
					//repaint
					return;
				}
				if (gt.getTile().isSelected() && GameBoard.getInstance().gametile == gt){
					gt.getTile().deSelet();
					gt.setIcon(new ImageIcon("src/images/"+gt.getTile().toString()+".png"));
					System.out.println("GT: "+gt.getTile()+" "+gt.getTile().isSelected());
					GameBoard.getInstance().gametile = null;
				}else{
					if (GameBoard.getInstance().gametile != null){
						return;
					}
					Set set = ((GameBoardRow)gt.getParent()).s;
					boolean oneSelected = false;
					for (Tile t : set){
						if (t.isSelected()){
							oneSelected = true;
						}
						GameBoard.getInstance().gametile = null;
					}
					if (!oneSelected){
						gt.getTile().select();
						//LOL image stuff starts here
						try {
							BufferedImage bimg = ImageIO.read(new File("src/images/"+gt.getTile().toString()+".png"));
							for (int x = 0; x < bimg.getWidth(); x++) {
					            for (int y = 0; y < bimg.getHeight(); y++) {
					                int rgba = bimg.getRGB(x, y);
					                Color col = new Color(rgba, true);
					                col = new Color(255 - col.getRed(),255 - col.getGreen(),255 - col.getBlue());
					                bimg.setRGB(x, y, col.getRGB());
					            }
					        }
							gt.setIcon(new ImageIcon(bimg));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						GameBoard.getInstance().gametile = gt;
						//LOL image stuff ends here
					}
					System.out.println("GT: "+gt.getTile()+" "+gt.getTile().isSelected());
				}
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
		super.repaint();
	}
}
