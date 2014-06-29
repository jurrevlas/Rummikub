package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JSplitPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import server.Server;
import message.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class Clientgui {

	private static Clientgui gui = new Clientgui();
	
	private JFrame frame;
	protected String playerName;
	private Chat pnlChat;
	private Connection con;
	private Server server;
	private GameBoard gameboard;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientgui window = Clientgui.getInstance();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public static Clientgui getInstance(){
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
		gameboard = new GameBoard();
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
	}

	
	public void sendMessage(Message message){
		if(gui.con != null)
			gui.con.sendMessage(message);
		//TODO else get angry
			
	}



	public boolean addConnection(String ip, int port){
		try {
			gui.con = new Connection(ip,port);
			gui.con.start();
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void handleMessage(Message message){
		
		if(message instanceof Introduction)
			sendMessage(new Introduction(gui.playerName));

		if(message instanceof SendHand){
			gameboard.setHand(((SendHand) message).getHand());
		}
		

	}
}