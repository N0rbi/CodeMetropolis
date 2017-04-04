package codemetropolis.toolchain.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The customizer window. It works like the designer in VisualStudio / AndroidStudio etc. It has a drag and drop tab and
 * an xml editor tab.
 * 
 * @author N0rbi
 *
 */
public class CustomMapperGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8111464758014701842L;
	private CustomMapperController controller;
	
	/**
	 * Instantiates the customizer window.
	 * @param controller The {@link CustomMapperController} instance.
	 */
	public CustomMapperGUI(CustomMapperController controller) {
		this.controller = controller;
		JPanel panel = createBasePanel();
	    
	    this.setResizable(false);
	    this.setContentPane(panel);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	  /**
	   * Creates the base panel for the Customizer GUI.
	   *
	   * @return The generated {@link JPanel}.
	   */
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
