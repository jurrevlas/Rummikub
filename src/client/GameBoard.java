package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
		
		
		//FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 5, 10);
		
		JPanel centerpanel = new JPanel();
		new BoxLayout(centerpanel, BoxLayout.Y_AXIS);
		
		FlowLayout fl1 = new FlowLayout(FlowLayout.LEFT, 5, 5);
		
		JPanel jp1 = new JPanel(fl1);
		
		jp1.add(new JLabel(tiles.get("Blk2")));
		jp1.add(new JLabel(tiles.get("Blk2")));
		jp1.add(new JLabel(tiles.get("Blk2")));
		jp1.add(new JLabel(tiles.get("Blk2")));
		jp1.add(Box.createRigidArea(new Dimension(60,86)));
		jp1.add(new JLabel(tiles.get("Blk2")));
		jp1.add(new JLabel(tiles.get("Blk2")));
		jp1.add(new JLabel(tiles.get("Blk2")));
		jp1.add(new JLabel(tiles.get("Blk2")));
		jp1.add(new JLabel(tiles.get("Blk2")));
		jp1.add(new JLabel(tiles.get("Blk2")));
		jp1.add(new JLabel(tiles.get("Blk2")));
		jp1.add(new JLabel(tiles.get("Blk2")));
		
		FlowLayout fl2 = new FlowLayout(FlowLayout.LEFT, 5, 5);
		
		JPanel jp2 = new JPanel(fl2);
		
		jp2.add(new JLabel(tiles.get("Blk2")));
		jp2.add(new JLabel(tiles.get("Blk2")));
		jp2.add(new JLabel(tiles.get("Blk2")));
		jp2.add(new JLabel(tiles.get("Blk2")));
		jp2.add(Box.createRigidArea(new Dimension(60,86)));
		jp2.add(new JLabel(tiles.get("Blk2")));
		jp2.add(new JLabel(tiles.get("Blk2")));
		jp2.add(new JLabel(tiles.get("Blk2")));
		jp2.add(new JLabel(tiles.get("Blk2")));
		jp2.add(new JLabel(tiles.get("Blk2")));
		jp2.add(new JLabel(tiles.get("Blk2")));
		jp2.add(new JLabel(tiles.get("Blk2")));
		jp2.add(new JLabel(tiles.get("Blk2")));

		centerpanel.add(jp1);
		centerpanel.add(jp2);
		add(centerpanel, BorderLayout.CENTER);
		
	}
}