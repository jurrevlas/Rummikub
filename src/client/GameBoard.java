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

import message.NewSet;

public class GameBoard extends JPanel{
	private static final long serialVersionUID = 1L;
	private static GameBoard gameboard = null;
	//Hashtable<String, ImageIcon> tiles = new Hashtable<String, ImageIcon>();
	GameBoardRow hand;
	GameTile gametile;
	JPanel centerpanel;
	
	public static GameBoard getInstance(){
		if (gameboard == null){
			GameBoard.gameboard = new GameBoard();
			return gameboard;
		}else{
			return gameboard;
		}
	}
	
	private GameBoard(){
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
		Set s = new Set();
		//s.add(new Tile(Color.Black, 1));
		hand = new GameBoardRow(s,true);
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
				
				if(gametile != null){
					System.out.println("Move from: "+Clientgui.getInstance().playerName);
					Tile t = gametile.getTile();
					t.deSelet();
					Clientgui.getInstance().sendMessage(new NewSet(Clientgui.getInstance().playerName,t));
				}
				
//				System.out.println(e.getSource());
//				
//				if (gametile != null){
//					gametile.getTile().deSelet();
//					Set s = new Set();
//					s.add(gametile.getTile());
//					centerpanel.add(new GameBoardRow(s));
//					((GameBoardRow)gametile.getParent()).s.remove(gametile.getTile());
//					gametile.invalidate();
//					centerpanel.invalidate();
//					/*
//					Component a = gametile;
//					while(a.getParent() != null){
//						a = a.getParent();
//						a.repaint();
//						a.validate();
//					}*/
//					gametile = null;
//				}
//				
//				/*
//				Set s = new Set();
//				s.add(new Tile(Color.Yellow, 12));
//				centerpanel.add(new GameBoardRow(s));
//				*/
//				
//				Clientgui.getInstance().repaint();
//				//Clientgui.getInstance().frame.repaint();
//				//Clientgui.getInstance().frame.validate();
				super.mouseClicked(e);
			}
		});
	}
	
	public void setHand(Set s){
		hand.s = s;
		hand.repaint();
	}
}