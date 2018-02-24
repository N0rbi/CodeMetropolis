package codemetropolis.toolchain.gui;

import codemetropolis.toolchain.gui.components.CMTextArea;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;





/**
 * CodeMetropolis Drag and Drop Element
 * This class represents a drag and drop from CMTextAreas. They are transparent and
 * we can change it's colors depends on status 
 * 
 * @author VEETAAA.SZE
 *
 */


public class CMDnDElement extends CMTextArea implements DropTargetListener {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 5943241178731741274L;
	

	
	DropTarget droptarget;
	Boolean acceptDrop = true;
	String tooltip = "";
	String displayMetricString = "";
	Boolean wasChecked = false;
	
	
	
	Color free = new Color(110, 230, 20, 100); //green
	Color occupied = new Color(240, 100, 70, 100); //red
	Color hover = new Color(250,160,20, 100); //orange
	
	

	public CMDnDElement(String tooltips, int x, int y, int width, int height) {
		setBounds(x, y, width, height);
		this.tooltip = tooltips;
		this.setText("");				  
		this.setOpaque(false);
		this.setEditable(false);
		this.setVisible(true);
		this.setToolTipText(tooltips);
		droptarget = new DropTarget(this, this);
		this.setBackground(free);
		
		
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
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		if(acceptDrop){
		setColorHover();
		}
		
	}
	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
	}
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub
		if(acceptDrop){
		setColorFree();
		}
	}
	@Override
	public void drop(DropTargetDropEvent dtde) {
		// TODO Auto-generated method stub
		if(acceptDrop){
		    try {
		        Transferable t = dtde.getTransferable();
	
		        if (dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		        	dtde.acceptDrop(dtde.getDropAction());
		        
		          
		          displayMetricString = (String) t.getTransferData(DataFlavor.stringFlavor);
	
		          this.setText(displayMetricString);
		          this.acceptDrop = false;	         
		          
		          
		          
		          dtde.dropComplete(true);
		          
		        } else
		        	dtde.rejectDrop();
		        } catch (java.io.IOException e2) {
		      	} catch (UnsupportedFlavorException e2) {
		      }
		    setColorOccupied();
		} else {
			dtde.rejectDrop();
		}
	}
	
}
