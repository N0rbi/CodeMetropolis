package codemetropolis.toolchain.gui.components;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.commons.lang3.ArrayUtils;

import codemetropolis.toolchain.gui.CustomMapperController;
import codemetropolis.toolchain.gui.components.listeners.SourceChangeListener;

public class CMDnDTabbedPane extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<CMDnDElement> elements = new LinkedList<CMDnDElement>();
	private CMDnDElement cellar1;
	private CMDnDElement cellar2;
	private CMDnDElement cellar3;
	private CMDnDElement cellar4;
	private CMDnDElement cellar5;
	private CMDnDElement cellar6;
	
	private CMDnDElement floor1;
	private CMDnDElement floor2;
	private CMDnDElement floor3;
	private CMDnDElement floor4;
	private CMDnDElement floor5;
	private CMDnDElement floor6;

	private CustomMapperController controller;
	
	public CMDnDTabbedPane(CMTextArea infoPanel, CustomMapperController controller){
		this.controller = controller;
		cellar1 = new CMDnDElement(controller.getCellar().getAttribute("width"),20,20,100,100, controller, infoPanel);
		cellar2 = new CMDnDElement(controller.getCellar().getAttribute("height"),140,20,100,100, controller, infoPanel);
		cellar3 = new CMDnDElement(controller.getCellar().getAttribute("length"),260,20,100,100, controller, infoPanel);
		cellar4 = new CMDnDElement(controller.getCellar().getAttribute("character"),20,150,100,100, controller, infoPanel);
		cellar5 = new CMDnDElement(controller.getCellar().getAttribute("external character"),140,140,100,100, controller, infoPanel);
		cellar6 = new CMDnDElement(controller.getCellar().getAttribute("torches"),320,180,100,100, controller, infoPanel);
		
		floor1 = new CMDnDElement(controller.getFloor().getAttribute("width"),20,20,100,100, controller, infoPanel);
		floor2 = new CMDnDElement(controller.getFloor().getAttribute("height"),140,20,100,100, controller, infoPanel);
		floor3 = new CMDnDElement(controller.getFloor().getAttribute("length"),260,20,100,100, controller, infoPanel);
		floor4 = new CMDnDElement(controller.getFloor().getAttribute("character"),20,150,100,100, controller, infoPanel);
		floor5 = new CMDnDElement(controller.getFloor().getAttribute("external character"),140,140,100,100, controller, infoPanel);
		floor6 = new CMDnDElement(controller.getFloor().getAttribute("torches"),320,180,100,100, controller, infoPanel);
		
		
	
		elements.add(cellar1);
		elements.add(cellar2);
		elements.add(cellar3);
		elements.add(cellar4);
		elements.add(cellar5);
		elements.add(cellar6);
		elements.add(floor1);
		elements.add(floor2);
		elements.add(floor3);
		elements.add(floor4);
		elements.add(floor5);
		elements.add(floor6);
	}
	
	public void addTabbedPanel(JPanel panel){
		
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
	            g.drawImage(cellarImage, 0, 30, panelWidth, panelHeight-30, null);
	        }			  
		};
		cellarPanel.setLayout(null);
		cellarPanel.setBounds(0, 30, panelWidth, panelHeight-30);
		
		CMComboBox<String> cSourceComponent = new CMComboBox<>(ArrayUtils.addAll(new String[]{"<<unset>>"}, controller.getSources()),
				0,0, 150, 30);
		
		cSourceComponent.addItemListener(new SourceChangeListener(controller.getCellar(), controller));
		
		controller.getCellar().addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getNewValue() == null) {
					cSourceComponent.setSelectedIndex(0);
				}else {
					cSourceComponent.setSelectedItem(evt.getNewValue());
				}
				
			}
		});
		
		cellarPanel.add(cSourceComponent);
		
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
	            g.drawImage(gardenImage, 0, 30, panelWidth, panelHeight-30, null);
	        }			  
		};
		gardenPanel.setLayout(null);
		gardenPanel.setBounds(0, 30, panelWidth, panelHeight-30);
		
		CMComboBox<String> gSourceComponent = new CMComboBox<>(ArrayUtils.addAll(new String[]{"<<unset>>"}, controller.getSources()),
				0,0, 150, 30);
		
		gSourceComponent.addItemListener(new SourceChangeListener(controller.getGarden(), controller));
		
		controller.getGarden().addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getNewValue() == null) {
					cSourceComponent.setSelectedIndex(0);
				}
			}
		});
		
		gardenPanel.add(gSourceComponent);
		
		
		
		
		//////////////////////floorTab///////////////////
		Image floorImage = requestImage("3");
		JPanel floorPanel = new JPanel(){
				/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

				protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(floorImage, 0, 30, panelWidth, panelHeight-30, null);
	        }			  
		};
		floorPanel.setLayout(null);
		floorPanel.setBounds(0, 30, panelWidth, panelHeight-30);
		
		CMComboBox<String> fSourceComponent = new CMComboBox<>(ArrayUtils.addAll(new String[]{"<<unset>>"}, controller.getSources()),
				0,0, 150, 30);
		
		//todo kiszervezni
		fSourceComponent.addItemListener(new SourceChangeListener(controller.getFloor(), controller));
		
		controller.getFloor().addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getNewValue() == null) {
					cSourceComponent.setSelectedIndex(0);
				}
			}
		});
		
		floorPanel.add(fSourceComponent);
		
		
		
		floorPanel.add(floor1);
		floorPanel.add(floor2);
		floorPanel.add(floor3);
		floorPanel.add(floor4);
		floorPanel.add(floor5);
		floorPanel.add(floor6);
		
		
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
	  
	public void clearAll() {
		
		for(CMDnDElement element : elements) {
			element.removeMetric();
		}
			
	}
}
