package codemetropolis.toolchain.gui.components.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import codemetropolis.toolchain.gui.CustomMapperController;
import codemetropolis.toolchain.gui.components.CMTextArea;
import codemetropolis.toolchain.gui.mapping.WrongMappingFormatException;


public class SaveListener implements ActionListener{
	
	private JFileChooser fileChooser;
	private CustomMapperController controller;
	private CMTextArea infoArea;
	
	public SaveListener(int fileSelectionMode, FileFilter filter, CustomMapperController controller, CMTextArea infoArea) {
		
		this.fileChooser = new JFileChooser();
	    this.fileChooser.setFileSelectionMode(fileSelectionMode);
	    if (filter != null) {
	      this.fileChooser.setFileFilter(filter);
	    }
	    this.controller = controller;
	    this.infoArea = infoArea;
	}

	  @Override
	  public void actionPerformed(ActionEvent event) {
		String output = "";
		try {
			output = controller.getOutputXml();
		} catch (WrongMappingFormatException e) {
			infoArea.append("ERROR: " + e.getMessage() + "\n");
			return;
		}
		

	    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	    	String saveLocation = fileChooser.getSelectedFile().getAbsolutePath();
	    	
	    	BufferedWriter writer = null;
	    	try
	    	{
	    	    writer = new BufferedWriter( new FileWriter(saveLocation));
	    	    writer.write(output);

	    	}
	    	catch ( IOException e)
	    	{
	    	}
	    	finally
	    	{
	    	    try
	    	    {
	    	        if ( writer != null)
	    	        writer.close();
	    	    }
	    	    catch ( IOException e)
	    	    {
	    	    	
	    	    }
	    	}
	    }
	  }

}
