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
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinGame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfIP;
	private JTextField tfPort;
	private final Clientgui gui;
	private JTextField tfPlayerName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JoinGame dialog = new JoinGame(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JoinGame(final Clientgui gui) {
		this.gui = gui;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblServer = new JLabel("Server:");
			GridBagConstraints gbc_lblServer = new GridBagConstraints();
			gbc_lblServer.insets = new Insets(0, 0, 5, 5);
			gbc_lblServer.gridx = 0;
			gbc_lblServer.gridy = 0;
			contentPanel.add(lblServer, gbc_lblServer);
		}
		{
			JLabel lblPlayerName = new JLabel("Player Name");
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
			gbc_tfPlayerName.gridx = 1;
			gbc_tfPlayerName.gridy = 1;
			contentPanel.add(tfPlayerName, gbc_tfPlayerName);
			tfPlayerName.setColumns(10);
		}
		{
			JLabel lblIp = new JLabel("IP:");
			GridBagConstraints gbc_lblIp = new GridBagConstraints();
			gbc_lblIp.anchor = GridBagConstraints.EAST;
			gbc_lblIp.insets = new Insets(0, 0, 5, 5);
			gbc_lblIp.gridx = 0;
			gbc_lblIp.gridy = 2;
			contentPanel.add(lblIp, gbc_lblIp);
		}
		{
			tfIP = new JTextField();
			tfIP.setText("localhost");
			GridBagConstraints gbc_tfIP = new GridBagConstraints();
			gbc_tfIP.insets = new Insets(0, 0, 5, 0);
			gbc_tfIP.gridx = 1;
			gbc_tfIP.gridy = 2;
			contentPanel.add(tfIP, gbc_tfIP);
			tfIP.setColumns(10);
		}
		{
			JLabel lblPort = new JLabel("Port:");
			GridBagConstraints gbc_lblPort = new GridBagConstraints();
			gbc_lblPort.anchor = GridBagConstraints.EAST;
			gbc_lblPort.insets = new Insets(0, 0, 0, 5);
			gbc_lblPort.gridx = 0;
			gbc_lblPort.gridy = 3;
			contentPanel.add(lblPort, gbc_lblPort);
		}
		{
			tfPort = new JTextField();
			tfPort.setText("12345");
			GridBagConstraints gbc_tfPort = new GridBagConstraints();
			gbc_tfPort.gridx = 1;
			gbc_tfPort.gridy = 3;
			contentPanel.add(tfPort, gbc_tfPort);
			tfPort.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gui.playerName = tfPlayerName.getText();
						gui.addConnection(tfIP.getText(),Integer.parseInt(tfPort.getText()));						
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
