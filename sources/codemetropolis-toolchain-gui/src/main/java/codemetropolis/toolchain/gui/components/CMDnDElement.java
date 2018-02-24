package codemetropolis.toolchain.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.dnd.DropTarget;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import codemetropolis.toolchain.gui.CustomMapperController;
import codemetropolis.toolchain.gui.components.listeners.MetricBindingChangeListener;
import codemetropolis.toolchain.gui.components.listeners.MetricDropListener;
import codemetropolis.toolchain.gui.mapping.MetricItemType;
import codemetropolis.toolchain.gui.mapping.attribute.BuildingAttribute;





/**
 * CodeMetropolis Drag and Drop Element
 * This class represents a drag and drop from CMTextAreas. They are transparent and
 * we can change it's colors depends on status 
 * 
 * @author VEETAAA.SZE
 *
 */


public class CMDnDElement extends CMTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5943241178731741274L;
	private static final Color free = new Color(110, 230, 20, 100); //green
	private static final Color occupied = new Color(240, 100, 70, 100); //red
	private static final Color hover = new Color(250,160,20, 100); //orange
	

	private BuildingAttribute attribute;
	
	private Boolean acceptDrop = true;
	private Boolean wasChecked = false;
	

	public CMDnDElement(BuildingAttribute attribute, int x, int y, int width, int height, CustomMapperController controller, CMTextArea infoPanel) {
		setBounds(x, y, width, height);
		this.attribute = attribute;
		this.setText("");				  
		this.setOpaque(false);
		this.setEditable(false);
		this.setVisible(true);
		this.setToolTipText(this.attribute.getName());
		this.setBackground(free);
		
		this.attribute.addPropertyChangeListener(new MetricBindingChangeListener(this));
		
		new DropTarget(this, new MetricDropListener(this, controller, infoPanel));
	}
	
	@Override
    protected void paintComponent(Graphics g)
    {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
	
	public void setColorFree(){
		this.setBackground(free);
	}
	
	public void setColorOccupied(){
		this.setBackground(occupied);
		
	}
	
	public void setColorHover(){
		this.setBackground(hover);
	}
	
	public void setAcceptDrop(boolean b) {
		this.acceptDrop = b;
	}
	
	public Boolean isAcceptDrop() {
		return acceptDrop;
	}

	public Boolean getWasChecked() {
		return wasChecked;
	}

	public BuildingAttribute getAttribute() {
		return attribute;
	}

	public void removeMetric() {
		this.setText("");
		acceptDrop = true;
		setColorFree();
		attribute.removeMetric();
	}
	
}
