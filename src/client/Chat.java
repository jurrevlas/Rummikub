package client;

import game.Color;
import game.Set;
import game.Tile;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JTextArea;

import message.ChatMessage;
import message.EndTurn;
import message.SendHand;
import message.StartGame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Chat extends JPanel {
	private JTextField tfInput;
	private JTextArea taOutput;
	private Clientgui client;
	private JButton btnStart;
	/**
	 * Create the panel.
	 */
	public Chat(final Clientgui client) {
		
		this.client = client;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		taOutput= new JTextArea();
		taOutput.setEditable(false);
		GridBagConstraints gbc_taOutput = new GridBagConstraints();
		gbc_taOutput.gridwidth = 2;
		gbc_taOutput.insets = new Insets(0, 0, 5, 5);
		gbc_taOutput.fill = GridBagConstraints.BOTH;
		gbc_taOutput.gridx = 0;
		gbc_taOutput.gridy = 0;
		add(taOutput, gbc_taOutput);
		
		tfInput = new JTextField();
		tfInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					sendMessage();
			}
		});
		GridBagConstraints gbc_tfInput = new GridBagConstraints();
		gbc_tfInput.insets = new Insets(0, 0, 0, 5);
		gbc_tfInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfInput.gridx = 0;
		gbc_tfInput.gridy = 1;
		add(tfInput, gbc_tfInput);
		tfInput.setColumns(10);
		
		JButton btnSend = new JButton("Send!");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.gridx = 1;
		gbc_btnSend.gridy = 1;
		add(btnSend, gbc_btnSend);
		
		btnStart = new JButton("Start!");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((JButton)e.getSource()).getText() == "Start!"){
					client.sendMessage(new StartGame(client.playerName));
				}
				if(((JButton)e.getSource()).getText() == "End Turn"){
					client.sendMessage(new EndTurn(client.playerName));
					((JButton)e.getSource()).setEnabled(false);
				}
			}
		});
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 2;
		gbc_btnStart.fill = 1;
		add(btnStart, gbc_btnStart);

	}
	
	public JButton getStartButton(){
		return btnStart;
	}
	
	public void sendMessage(){
		ChatMessage message = new ChatMessage(client.playerName,tfInput.getText()); 
		tfInput.setText("");
		client.sendMessage(message);
	}
	
	public void printMessage(String sender, String message){
		taOutput.append(sender+":"+message+"\n");
	}

}
