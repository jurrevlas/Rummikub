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
import message.Message;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Clientgui {

	private JFrame frame;
	protected String playerName;
	private Chat pnlChat;
	private Server server;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientgui window = new Clientgui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Clientgui() {
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
		
		JPanel GamePanel = new JPanel();
		splitPane.setLeftComponent(GamePanel);
		
		JMenuBar mainMenu = new JMenuBar();
		frame.setJMenuBar(mainMenu);
		
		JMenu Game = new JMenu("Game");
		mainMenu.add(Game);
		
		JMenuItem joinGame = new JMenuItem("Join Game");
		joinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinGame join = new JoinGame();
			}
		});
		Game.add(joinGame);
		
		JMenuItem createGame = new JMenuItem("Create Game");
		
		createGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateGame create = new CreateGame();
				
			}
		});
		
		JMenuItem quitGame = new JMenuItem("Quit Game");
		quitGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		Game.add(createGame);
	}
	
	
	public void sendMessage(Message message){
		//TODO
	}
	
	
	
}
