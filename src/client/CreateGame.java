package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import server.Server;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateGame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfPort;
	private JTextField maxPlayers;	
	private Clientgui gui;
	private JTextField tfPlayerName;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateGame dialog = new CreateGame(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateGame(final Clientgui gui) {
		this.gui = gui;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblServeroptions = new JLabel("Serveroptions:");
			GridBagConstraints gbc_lblServeroptions = new GridBagConstraints();
			gbc_lblServeroptions.insets = new Insets(0, 0, 5, 5);
			gbc_lblServeroptions.gridx = 0;
			gbc_lblServeroptions.gridy = 0;
			contentPanel.add(lblServeroptions, gbc_lblServeroptions);
		}
		{
			JLabel lblPlayerName = new JLabel("Player Name:");
			GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
			gbc_lblPlayerName.anchor = GridBagConstraints.EAST;
			gbc_lblPlayerName.insets = new Insets(0, 0, 5, 5);
			gbc_lblPlayerName.gridx = 0;
			gbc_lblPlayerName.gridy = 1;
			contentPanel.add(lblPlayerName, gbc_lblPlayerName);
		}
		{
			tfPlayerName = new JTextField();
			tfPlayerName.setText("Hanz");
			GridBagConstraints gbc_tfPlayerName = new GridBagConstraints();
			gbc_tfPlayerName.insets = new Insets(0, 0, 5, 0);
			gbc_tfPlayerName.gridx = 2;
			gbc_tfPlayerName.gridy = 1;
			contentPanel.add(tfPlayerName, gbc_tfPlayerName);
			tfPlayerName.setColumns(10);
		}
		{
			JLabel lblPort = new JLabel("Port:");
			GridBagConstraints gbc_lblPort = new GridBagConstraints();
			gbc_lblPort.anchor = GridBagConstraints.EAST;
			gbc_lblPort.insets = new Insets(0, 0, 5, 5);
			gbc_lblPort.gridx = 0;
			gbc_lblPort.gridy = 2;
			contentPanel.add(lblPort, gbc_lblPort);
		}
		{
			tfPort = new JTextField();
			tfPort.setText("12345");
			GridBagConstraints gbc_tfPort = new GridBagConstraints();
			gbc_tfPort.insets = new Insets(0, 0, 5, 0);
			gbc_tfPort.gridx = 2;
			gbc_tfPort.gridy = 2;
			contentPanel.add(tfPort, gbc_tfPort);
			tfPort.setColumns(10);
		}
		{
			JLabel lblMaxPlayers = new JLabel("Max Players:");
			GridBagConstraints gbc_lblMaxPlayers = new GridBagConstraints();
			gbc_lblMaxPlayers.anchor = GridBagConstraints.EAST;
			gbc_lblMaxPlayers.insets = new Insets(0, 0, 0, 5);
			gbc_lblMaxPlayers.gridx = 0;
			gbc_lblMaxPlayers.gridy = 3;
			contentPanel.add(lblMaxPlayers, gbc_lblMaxPlayers);
		}
		{
			maxPlayers = new JTextField();
			maxPlayers.setText("4");
			GridBagConstraints gbc_maxPlayers = new GridBagConstraints();
			gbc_maxPlayers.gridx = 2;
			gbc_maxPlayers.gridy = 3;
			contentPanel.add(maxPlayers, gbc_maxPlayers);
			maxPlayers.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Server server = new Server(Integer.decode(tfPort.getText()));
						server.start();
						gui.playerName = tfPlayerName.getText();
						gui.addConnection("localhost", Integer.decode(tfPort.getText()));
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}

}
