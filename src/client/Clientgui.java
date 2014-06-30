package client;

import game.Set;
import game.Tile;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.RepaintManager;

import server.Server;
import message.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class Clientgui {

	private static Clientgui gui = new Clientgui();
	
	public JFrame frame;
	protected String playerName;
	private Chat pnlChat;
	private Connection con;
	private Server server;
	private GameBoard gameboard;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientgui window = Clientgui.getInstance();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		})*/;
		Clientgui.getInstance();
	}
	

	public void repaint(){
		
		frame.validate();
		frame.repaint();
	}
	
	public static Clientgui getInstance(){
		if(gui == null){
			gui = new Clientgui();
			return  gui;
		}
		else
			return gui;
	}
	
	
	/**
	 * Create the application.
	 */
	private Clientgui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.9);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		frame.getContentPane().add(splitPane, gbc_splitPane);
		
		pnlChat = new Chat(this);
		splitPane.setRightComponent(pnlChat);
		
		//JPanel GamePanel = new JPanel();
		//GamePanel.add(new GameBoard());
		gameboard = GameBoard.getInstance();
		splitPane.setLeftComponent(gameboard);
		
		JMenuBar mainMenu = new JMenuBar();
		frame.setJMenuBar(mainMenu);
		
		JMenu Game = new JMenu("Game");
		mainMenu.add(Game);
		
		JMenuItem joinGame = new JMenuItem("Join Game");
		joinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinGame join = new JoinGame(gui);
			}
		});
		Game.add(joinGame);
		
		JMenuItem createGame = new JMenuItem("Create Game");
		
		createGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateGame create = new CreateGame(gui);
				
			}
		});
		
		Game.add(createGame);
		
		JMenuItem quitGame = new JMenuItem("Quit Game");
		quitGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		
		Game.add(createGame);
		Game.add(quitGame);
		frame.setVisible(true);
	}

	
	public void sendMessage(Message message){
		if(gui.con != null)
			gui.con.sendMessage(message);
		//TODO else get angry
			
	}



	public boolean addConnection(String ip, int port){
		try {
			gui.con = new Connection(ip,port,this);
			gui.con.start();
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void handleMessage(Message message){
		if(message instanceof Introduction){
			sendMessage(new Introduction(gui.playerName));
		}

		if(message instanceof SendHand){
			gameboard.setHand(((SendHand) message).getHand());
			gameboard.invalidate();
			gameboard.hand.invalidate();
			frame.validate();
			//gui.pnlChat.printMessage(message.getSender(),message.getMessage());
		}
		
		if(message instanceof NewSet){
			//System.out.println("Client/Message/NewSet");
			Set s = ((NewSet)message).getSet();
			//System.out.println(message.getSender()+" "+playerName);
			if(message.getSender().equals(playerName)){
				gameboard.gametile = null;
				gameboard.hand.s.remove(s.getFirst());
				gameboard.hand.invalidate();
				gameboard.hand.validate();
				gameboard.hand.repaint();
			}
			GameBoardRow gbr = new GameBoardRow(s,false);
			gameboard.centerpanel.add(gbr);
			gameboard.centerpanel.invalidate();
			frame.validate();
			gui.pnlChat.printMessage(message.getSender(),message.getMessage());
		}
		
		if(message instanceof AddToSet){
			//System.out.println("Client/Message/AddToSet");
			AddToSet ats = (AddToSet)message;
			Tile t = ats.getTile();
			if(ats.getSender().equals(playerName)){
				gameboard.gametile = null;
				gameboard.hand.s.remove(t);
				gameboard.hand.invalidate();
				gameboard.hand.validate();
				gameboard.hand.repaint();
			}
			for(Component c : gameboard.centerpanel.getComponents()){
				if(c instanceof GameBoardRow){
					GameBoardRow gbr = (GameBoardRow)c;
					if (gbr.s.equals(ats.getDestination())){
						gbr.s.add(t);
						gbr.invalidate();
						Component a = gbr;
						while(a.getParent() != null){
							a.repaint();
							a.validate();
							a = a.getParent();
							a.repaint();
							a.validate();
						}
					}
				}
			}
			gameboard.centerpanel.invalidate();
			gameboard.invalidate();
			frame.validate();
			frame.repaint();
			gui.pnlChat.printMessage(message.getSender(),message.getMessage());
		}
		
		if(message instanceof MoveToSet){
			MoveToSet mts = (MoveToSet)message;
			gameboard.gametile = null;
			for(Component c : gameboard.centerpanel.getComponents()){
				if(c instanceof GameBoardRow){
					GameBoardRow gbr = (GameBoardRow)c;
					if (gbr.s.equals(mts.getDestination())){
						gbr.s.add(mts.getTile());
						gbr.invalidate();
						Component a = gbr;
						while(a.getParent() != null){
							a.repaint();
							a.validate();
							a = a.getParent();
							a.repaint();
							a.validate();
						}
					}
					if (gbr.s.equals(mts.getSource())){
						gbr.s.remove(mts.getTile());
						if(gbr.s.isEmpty()){
							gbr.getParent().remove(gbr);
						}
						gbr.invalidate();
						Component a = gbr;
						while(a.getParent() != null){
							a.repaint();
							a.validate();
							a = a.getParent();
							a.repaint();
							a.validate();
						}
					}
				}
			}
			gameboard.centerpanel.invalidate();
			gameboard.invalidate();
			frame.validate();
			frame.repaint();
			gui.pnlChat.printMessage(message.getSender(),message.getMessage());
		}
		
		if(message instanceof MoveToNewSet){
			MoveToNewSet mtns = (MoveToNewSet)message;
			gameboard.gametile = null;
			for(Component c : gameboard.centerpanel.getComponents()){
				if(c instanceof GameBoardRow){
					GameBoardRow gbr = (GameBoardRow)c;
					if (gbr.s.equals(mtns.getSource())){
						gbr.s.remove(mtns.getTile());
						if(gbr.s.isEmpty()){
							gbr.getParent().remove(gbr);
						}
						gbr.invalidate();
						Component a = gbr;
						while(a.getParent() != null){
							a.repaint();
							a.validate();
							a = a.getParent();
							a.repaint();
							a.validate();
						}
					}
				}
			}
			GameBoardRow gbr = new GameBoardRow(mtns.getDestination(),false);
			gameboard.centerpanel.add(gbr);
			gameboard.centerpanel.invalidate();
			gameboard.invalidate();
			frame.validate();
			frame.repaint();
		}
		
		if(message instanceof MoveToHand){
			MoveToHand mth = (MoveToHand)message;
			gameboard.gametile = null;
			for(Component c : gameboard.centerpanel.getComponents()){
				if(c instanceof GameBoardRow){
					GameBoardRow gbr = (GameBoardRow)c;
					if (gbr.s.equals(mth.getSource())){
						gbr.s.remove(mth.getTile());
						if(gbr.s.isEmpty()){
							gbr.getParent().remove(gbr);
						}
						gbr.invalidate();
						Component a = gbr;
						while(a.getParent() != null){
							a.repaint();
							a.validate();
							a = a.getParent();
							a.repaint();
							a.validate();
						}
					}
				}
			}
			if(message.getSender().equals(playerName)){
				gameboard.hand.s.add(mth.getTile());
				gameboard.hand.invalidate();
				gameboard.hand.validate();
				gameboard.hand.repaint();
			}
			gameboard.centerpanel.invalidate();
			gameboard.invalidate();
			frame.validate();
			frame.repaint();
		}
		
		if(message instanceof YourTurn){
			gui.pnlChat.printMessage(message.getSender(),message.getMessage());
			gui.pnlChat.getStartButton().setEnabled(true);
			//System.out.println("MY TURN");
		}
		
		if(message instanceof StartGame){
			gui.pnlChat.getStartButton().setText("End Turn");
			gui.pnlChat.getStartButton().setEnabled(false);
			//System.out.println("STARTORO");
		}
		
		if(message instanceof SendTable){
			SendTable st = (SendTable)message;
			gameboard.remove(gameboard.centerpanel);
			gameboard.centerpanel = new JPanel();
			gameboard.centerpanel.setBackground(java.awt.Color.darkGray);
			new BoxLayout(gameboard.centerpanel, BoxLayout.Y_AXIS);
			gameboard.add(gameboard.centerpanel, BorderLayout.CENTER);
			for(Set s : st.getTable()){
				gameboard.centerpanel.add(new GameBoardRow(s, false));
			}
		}
		
		if(message instanceof ChatMessage){
			gui.pnlChat.printMessage(((ChatMessage) message ).getSender(), ((ChatMessage) message ).getMessage());
		}
		

	}
}