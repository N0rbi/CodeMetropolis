package codemetropolis.toolchain.gui;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import codemetropolis.toolchain.gui.beans.ExecutionException;
import codemetropolis.toolchain.gui.beans.ExecutionOptions;
import codemetropolis.toolchain.gui.executors.ConverterToolExecutor;
import codemetropolis.toolchain.gui.executors.MetricGeneratorExecutor;
import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;
import codemetropolis.toolchain.gui.utils.GuiUtils;
import codemetropolis.toolchain.gui.utils.Translations;

/**
 * Controller or the {@link CustomMapperGUI}. It does the xml parsing and stores the generated mapping xml.
 * 
 * @author N0rbi
 *
 */
public class CustomMapperController {
	
	public static String[] filterMetrics = new String[] {"source_id", "Name", "LongName"};
	private File tempFolder;
	
	private ExecutionOptions executionOptions;
	
	public CustomMapperController(ExecutionOptions executionOptions) {
		this.executionOptions = executionOptions;
	}
	
	  /**
	   * Handles toolchain execution until the converter tool. The output will be in the temporary folder.
	   * 
	   * @param outout The {@link PrintStream} instance that will be set for each executor, so we can read their outputs and
	   *          display them for the user.
	   * @throws ExecutionException if any exception occurs during execution.
	   */
	  public void executeUntilConverter(PrintStream out) throws ExecutionException {
		  try {
			  if(this.tempFolder == null){
				  this.tempFolder = createTempFolder();
			  }
			  
		      new MetricGeneratorExecutor().execute(tempFolder, executionOptions, out);
		      new ConverterToolExecutor().execute(tempFolder, executionOptions, out);
		      
		  } catch (Exception e) {
			  throw new ExecutionException("Toolchain execution failed!", e);
		  }
	  }
	
	  /**
	   * Creates the temporary folder to store values until the program runs.
	   * 
	   * @return The {@link File} object that is referenced as temporary folder.
	   * @throws ExecutionException
	   */
	  private File createTempFolder() throws ExecutionException {
		  
		File cmRoot = new File(executionOptions.getMinecraftRoot().getAbsolutePath() + File.separator + ".code-metropolis");
		if (!cmRoot.exists()) {
		  cmRoot.mkdir();
		}
		
		File tempFolder = GuiUtils.getTempFolder(executionOptions);
		
		if(tempFolder.exists()){
			try {
				GuiUtils.deleteDirectory(tempFolder);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!tempFolder.mkdir()) {
			throw new ExecutionException(Translations.t("gui_err_mkdir_project_failed"));
	    }
		
		Runtime.getRuntime().addShutdownHook(new Thread() {

		      @Override
		      public void run() {
		    	  try {
		    		  if (tempFolder.exists()) {
		    			 GuiUtils.deleteDirectory(tempFolder); 
		    		  }
		    	  } catch(Exception e) {
		    		  //we can't do anything at this point
		    	  }
		      }
		 });
		
	    return tempFolder;
	  }
	
	
	/**
	 * 
	 * @return The metrics list parsed from the temp folder.
	 */
	public List<MetricItemType> parseMetricsFromTempFile(){
		
		HashSet<MetricItemType> metrics = new HashSet<>();
		
		try {
		File converterResult = new File(GuiUtils.getTempFolder(executionOptions), "converter-results.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(converterResult);
		
		NodeList properties = doc.getElementsByTagName("property");
		
		for (int i=0; i<properties.getLength(); i++) {
			Element property = (Element) properties.item(i);
			
			MetricItemType item = new MetricItemType();
			
			item.setName(property.getAttribute("name"));
			MetricDataType dataType = MetricDataType.unknown;
			switch (property.getAttribute("type")) {
				case "string":
					dataType = MetricDataType.string;
					break;
				case "int":
					dataType = MetricDataType.integer;
					break;
				case "float":
					dataType = MetricDataType.normalizedFloat;
					break;
			}
			item.setDataType(dataType);
			metrics.add(item);
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>(metrics);
	}

}
