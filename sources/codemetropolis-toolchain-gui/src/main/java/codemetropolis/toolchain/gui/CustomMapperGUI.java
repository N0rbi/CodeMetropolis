package codemetropolis.toolchain.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomMapperGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8111464758014701842L;
	private CustomMapperController controller;
	
	public CustomMapperGUI(CustomMapperController controller) {
		this.controller = controller;
		JPanel panel = createBasePanel();
	    
	    this.setResizable(false);
	    this.setContentPane(panel);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	private static final JPanel createBasePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 800, 650);
	
		Dimension size = new Dimension(800, 650);
		panel.setMinimumSize(size);
		panel.setPreferredSize(size);
		panel.setMaximumSize(size);
	
		return panel;
	}

}
