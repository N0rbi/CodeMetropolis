package codemetropolis.toolchain.gui.components.listeners;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import codemetropolis.toolchain.gui.CustomMapperController;
import codemetropolis.toolchain.gui.components.CMDnDElement;
import codemetropolis.toolchain.gui.components.CMTextArea;
import codemetropolis.toolchain.gui.mapping.MetricItemType;
import codemetropolis.toolchain.gui.mapping.attribute.ConversionMismatchException;

public class MetricDropListener implements DropTargetListener {
	
	private CMDnDElement element;
	private CustomMapperController controller;
	private CMTextArea infoPanel;
	
	
	public MetricDropListener(CMDnDElement element, CustomMapperController controller, CMTextArea infoPanel) {
		this.element = element;
		this.controller = controller;
		this.infoPanel = infoPanel;
	}
	
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		if(element.isAcceptDrop()){
		element.setColorHover();
		}
		
	}
	@Override
	public void dragExit(DropTargetEvent dte) {
		if(element.isAcceptDrop()){
		element.setColorFree();
		}
	}
	@Override
	public void drop(DropTargetDropEvent dtde) {
		if(element.isAcceptDrop()){
			element.setColorOccupied();
		    try {
		        Transferable t = dtde.getTransferable();
	
		        if (dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		        	dtde.acceptDrop(dtde.getDropAction());
		        
		          String metricString = (String) t.getTransferData(DataFlavor.stringFlavor);
		          MetricItemType metric = controller.getMetricByString(metricString);
		          if(metric == null){
		        	  return;
		          }
		          try{
		        	  element.getAttribute().setMetricType(metric);
		        	  controller.addOrReplaceMetrics(metric.getName(), element.getAttribute());
			          element.setAcceptDrop(false);
			          dtde.dropComplete(true);
			          infoPanel.append(element.getAttribute().getMetricType().getName() + " was assigned to "+ element.getAttribute().getName()+"\n");
		          } catch (ConversionMismatchException e) {
		        	  infoPanel.append("ERROR: " + e.getMessage() + "\n");
		          }
		          
		        } else{
		        	dtde.rejectDrop();
		        }
	        } catch (java.io.IOException|UnsupportedFlavorException e) {
	        	e.printStackTrace();
	        }
		} else {
			dtde.rejectDrop();
		}
	}
	

	@Override
	public void dragOver(DropTargetDragEvent dtde) {}
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {}

}
