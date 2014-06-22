package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GameBoard extends JPanel{
	private static final long serialVersionUID = 1L;
	
	Hashtable<String, ImageIcon> tiles = new Hashtable<String, ImageIcon>();
	
	public GameBoard(){
		
		for(game.Color c : game.Color.values()){
			for(int i = 1; i<=13; i++){
				tiles.put(c.toString()+i, new ImageIcon("src/images/"+c.toString()+i+".png"));
			}
		}
		tiles.put("Blkj", new ImageIcon("src/images/Blkj.png"));
		tiles.put("Redj", new ImageIcon("src/images/Redj.png"));
		
		setBackground(Color.black);
		setLayout(new BorderLayout());
		
		FlowLayout flow = new FlowLayout();
		JPanel lowerpanel = new JPanel(flow);
		
		lowerpanel.add(new JLabel(tiles.get("Yel1")));
		lowerpanel.add(new JLabel(tiles.get("Blk2")));
		lowerpanel.add(new JLabel(tiles.get("Redj")));
		
		JScrollPane sp = new JScrollPane(lowerpanel,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
	            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		add(sp, BorderLayout.PAGE_END);
		
		FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 5, 10);
		JPanel centerpanel = new JPanel(fl);
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel("               "));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		centerpanel.add(new JLabel(tiles.get("Blk2")));
		
		add(centerpanel, BorderLayout.CENTER);
		
	}
}