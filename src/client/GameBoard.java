package client;

import game.Color;
import game.Set;
import game.Tile;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.MouseInputAdapter;

public class GameBoard extends JPanel{
	private static final long serialVersionUID = 1L;
	//Hashtable<String, ImageIcon> tiles = new Hashtable<String, ImageIcon>();
	GameBoardRow hand;
	Object selection[];
	JPanel centerpanel;
	public GameBoard(){
		/*
		for(game.Color c : game.Color.values()){
			for(int i = 1; i<=13; i++){
				tiles.put(c.toString()+i, new ImageIcon("src/images/"+c.toString()+i+".png"));
			}
		}
		tiles.put("Blkj", new ImageIcon("src/images/Blkj.png"));
		tiles.put("Redj", new ImageIcon("src/images/Redj.png"));
		*/
		
		setBackground(java.awt.Color.darkGray);
		setLayout(new BorderLayout());
		hand = new GameBoardRow(new Set());
		JScrollPane sp = new JScrollPane(hand,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		add(sp, BorderLayout.PAGE_END);
		
		centerpanel = new JPanel();
		centerpanel.setBackground(java.awt.Color.darkGray);
		new BoxLayout(centerpanel, BoxLayout.Y_AXIS);
		
		//Set s = new Set();
		
		//centerpanel.add(new GameBoardRow(s));
		add(centerpanel, BorderLayout.CENTER);
		
		addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getSource());
				Set s = new Set();
				s.add(new Tile(Color.Yellow, 12));
				centerpanel.add(new GameBoardRow(s));
				Component a = (Component)e.getSource();
				while(a.getParent() != null){
					a = a.getParent();
					a.repaint();
					a.validate();
				}
				super.mouseClicked(e);
			}
		});
	}
	
	public void setHand(Set s){
		hand.s = s;
		hand.repaint();
	}
}