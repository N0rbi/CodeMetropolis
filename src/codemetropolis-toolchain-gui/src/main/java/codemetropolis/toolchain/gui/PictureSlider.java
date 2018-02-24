package codemetropolis.toolchain.gui;

import java.awt.CardLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PictureSlider extends JFrame{

	
	private CardLayout cardLayout;
	JPanel mainPanel = new JPanel();

	void initComponents(){
		JButton next = new JButton("NEXT");
		JButton back = new JButton("BACK");
	}
	
	void initImage(){
		String[] fileName = {"1.jpg", "2.jpg"};
		for(String s: fileName){
			Icon icon = new ImageIcon("src/img/"+s);
			JLabel label = new JLabel(icon);
			mainPanel.add(label);
		}
		cardLayout = new CardLayout();
		mainPanel.setLayout(cardLayout);
	}
	
	public PictureSlider(){
		initComponents();
		initImage();
	}
}
