package codemetropolis.toolchain.gui.components;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JPanel;

public abstract class CMBuildingTargetComponent extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1078456662917214891L;
	private final LinkedList<CMDnDElement> elements;
	private Image image;
	
	private static final int panelWidth = 490;
	private static final int panelHeight = 490;
	
	public CMBuildingTargetComponent(Image i, CMDnDElement... elements) {
		this.elements = new LinkedList<>(Arrays.asList(elements));
		this.image = i;
		for(CMDnDElement e : this.elements) {
			this.add(e);
		}
	}
	
	public void clearAll() {
		for(CMDnDElement element : elements) {
			element.removeMetric();
		}
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 30, panelWidth, panelHeight-30, null);
    }			  

}
