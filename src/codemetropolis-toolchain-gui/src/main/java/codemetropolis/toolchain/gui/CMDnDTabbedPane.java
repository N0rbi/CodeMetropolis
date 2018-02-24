package codemetropolis.toolchain.gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CMDnDTabbedPane extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;





	/**
	 * 
	 */
	public CMDnDTabbedPane(){
		
	}
	
	LinkedList<CMDnDElement> element = new LinkedList<CMDnDElement>();
	
	CMDnDElement cellar1 = new CMDnDElement("width",20,20,100,100);
	CMDnDElement cellar2 = new CMDnDElement("height",140,20,100,100);
	CMDnDElement cellar3 = new CMDnDElement("length",260,20,100,100);
	CMDnDElement cellar4 = new CMDnDElement("character",20,150,100,100);
	CMDnDElement cellar5 = new CMDnDElement("external character",140,140,100,100);
	CMDnDElement cellar6 = new CMDnDElement("torches",320,180,100,100);
	
	CMDnDElement floor1 = new CMDnDElement("width",20,20,100,100);
	CMDnDElement floor2 = new CMDnDElement("height",140,20,100,100);
	CMDnDElement floor3 = new CMDnDElement("length",260,20,100,100);
	CMDnDElement floor4 = new CMDnDElement("character",20,150,100,100);
	CMDnDElement floor5 = new CMDnDElement("external character",140,140,100,100);
	CMDnDElement floor6 = new CMDnDElement("torches",320,180,100,100);

	
	CMDnDElement garden1 = new CMDnDElement("flower-ratio",20,20,100,100);
	CMDnDElement garden2 = new CMDnDElement("tree-ratio",260,20,100,100);
	CMDnDElement garden3 = new CMDnDElement("mushroom-ratio",320,180,100,100);

	
	
	
	
	public void addTabbedPanel(JPanel panel){
		
		int panelWidth = 490;
		int panelHeight = 490;
		
		JPanel tabbedPanel = new JPanel();
		tabbedPanel.setBounds(20, 130, 500, 500);
		tabbedPanel.setBackground(Color.white);
		
		///////////////cellarTab////////////////
		Image cellarImage = requestImage("1");
		JPanel cellarPanel = new JPanel(){
				/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

				protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(cellarImage, 0, 0,panelWidth,panelHeight, null);
	        }			  
		};
		cellarPanel.setLayout(null);
		cellarPanel.setBounds(0, 0, panelWidth, panelHeight);
		
		element.add(cellar1);
		element.add(cellar2);
		element.add(cellar3);
		element.add(cellar4);
		element.add(cellar5);
		element.add(cellar6);
		
		cellarPanel.add(cellar1);
		cellarPanel.add(cellar2);
		cellarPanel.add(cellar3);
		cellarPanel.add(cellar4);
		cellarPanel.add(cellar5);
		cellarPanel.add(cellar6);
		
		/////////gardenTab//////////////////////
		Image gardenImage = requestImage("2");
		JPanel gardenPanel = new JPanel(){
				/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

				protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(gardenImage, 0, 0,panelWidth,panelHeight, null);
	        }			  
		};
		gardenPanel.setLayout(null);
		gardenPanel.setBounds(0, 0, panelWidth, panelHeight);
		
		gardenPanel.add(garden1);
		gardenPanel.add(garden2);
		gardenPanel.add(garden3);
		
		element.add(garden1);
		element.add(garden2);
		element.add(garden3);
		
		
		//////////////////////floorTab///////////////////
		Image floorImage = requestImage("3");
		JPanel floorPanel = new JPanel(){
				/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

				protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(floorImage, 0, 0,panelWidth,panelHeight, null);
	        }			  
		};
		floorPanel.setLayout(null);
		floorPanel.setBounds(0, 0, panelWidth, panelHeight);
		
		
		floorPanel.add(floor1);
		floorPanel.add(floor2);
		floorPanel.add(floor3);
		floorPanel.add(floor4);
		floorPanel.add(floor5);
		floorPanel.add(floor6);
		
		element.add(floor1);
		element.add(floor2);
		element.add(floor3);
		element.add(floor4);
		element.add(floor5);
		element.add(floor6);
		
		
		tabbedPanel.setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 495, 495);
		tabbedPane.add("Cellar", cellarPanel);
		tabbedPane.add("Garden", gardenPanel);
		tabbedPane.add("Floor", floorPanel);
		tabbedPanel.add(tabbedPane);
		tabbedPanel.setVisible(true);
		
		panel.add(tabbedPanel);	
	}
	
	  private Image requestImage(String s) {
	        Image image = null;

	        try {	        	
	        	image = ImageIO.read(getClass().getResourceAsStream("/images/minecraftexamples/"+s+".png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return image;
	    }

	public String lastModified() {
		String modifiedString = "";
		@SuppressWarnings("unused")
		int size = element.size();
		for(CMDnDElement dnd : element){
			if(dnd.acceptDrop == false && dnd.wasChecked == false){
				modifiedString = dnd.displayMetricString + " to "+  dnd.tooltip ;
				dnd.wasChecked = true;
			}
		}	
		
		return modifiedString;
	}
}
