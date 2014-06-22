package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.ScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GameBoard extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private ImageIcon tiles[];
	
	public GameBoard(){
		ImageIcon img1 = new ImageIcon("src/images/b1.png");
		ImageIcon img2 = new ImageIcon("src/images/b2.png");
		ImageIcon img3 = new ImageIcon("src/images/b3.png");
		ImageIcon img4 = new ImageIcon("src/images/b4.png");
		ImageIcon img5 = new ImageIcon("src/images/b5.png");
		ImageIcon img6 = new ImageIcon("src/images/b6.png");
		ImageIcon img7 = new ImageIcon("src/images/b7.png");
		ImageIcon img8 = new ImageIcon("src/images/b8.png");
		ImageIcon img9 = new ImageIcon("src/images/b9.png");
		ImageIcon img10 = new ImageIcon("src/images/b10.png");
		ImageIcon img11 = new ImageIcon("src/images/b11.png");
		ImageIcon img12 = new ImageIcon("src/images/b12.png");
		ImageIcon img13 = new ImageIcon("src/images/b13.png");
		
		
		setBackground(Color.black);
		setLayout(new BorderLayout());
		
		FlowLayout flow = new FlowLayout();
		JPanel lowerpanel = new JPanel(flow);
		
		lowerpanel.add(new JLabel(img1));
		lowerpanel.add(new JLabel(img2));
		lowerpanel.add(new JLabel(img3));
		lowerpanel.add(new JLabel(img4));
		lowerpanel.add(new JLabel(img5));
		lowerpanel.add(new JLabel(img6));
		lowerpanel.add(new JLabel(img7));
		lowerpanel.add(new JLabel(img8));
		lowerpanel.add(new JLabel(img9));
		lowerpanel.add(new JLabel(img10));
		lowerpanel.add(new JLabel(img11));
		lowerpanel.add(new JLabel(img12));
		lowerpanel.add(new JLabel(img13));
		
		JScrollPane sp = new JScrollPane(lowerpanel,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
	            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		add(sp, BorderLayout.PAGE_END);
	}
}