package codemetropolis.toolchain.gui;

import java.io.File;
import java.io.PrintStream;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import codemetropolis.toolchain.gui.beans.ExecutionException;
import codemetropolis.toolchain.gui.beans.ExecutionOptions;
import codemetropolis.toolchain.gui.executors.ConverterToolExecutor;
import codemetropolis.toolchain.gui.executors.MetricGeneratorExecutor;
import codemetropolis.toolchain.gui.mapping.MetricDataType;
import codemetropolis.toolchain.gui.mapping.MetricItemType;
import codemetropolis.toolchain.gui.mapping.WrongMappingFormatException;
import codemetropolis.toolchain.gui.mapping.attribute.AttributeFactory;
import codemetropolis.toolchain.gui.mapping.attribute.BuildingAttribute;
import codemetropolis.toolchain.gui.mapping.attribute.ConversionMismatchException;
import codemetropolis.toolchain.gui.mapping.attribute.UnknownAttributeException;
import codemetropolis.toolchain.gui.mapping.targettable.AttributeDuplicateException;
import codemetropolis.toolchain.gui.mapping.targettable.BuildingTargettable;
import codemetropolis.toolchain.gui.mapping.targettable.TargetFactory;
import codemetropolis.toolchain.gui.mapping.targettable.UnknownTargetException;
import codemetropolis.toolchain.gui.mapping.targettable.WrongAttributeException;
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
	private HashSet<MetricItemType> metrics;
	
	public CustomMapperController(ExecutionOptions executionOptions) {
		this.executionOptions = executionOptions;
		MetricItemType metric = new MetricItemType();
		metric.setName("Próba");
		metric.setDataType(MetricDataType.getMetricDataType("INTEGER"));
		AttributeFactory a = new AttributeFactory();
		TargetFactory t = new TargetFactory();
		BuildingTargettable g = null;
		BuildingTargettable f = null;
		BuildingTargettable c = null;
		BuildingAttribute ba = null;
		try {
			ba = a.getBuildingAttribute("WIDTH", metric);
			f = t.getTarget("FLOOR", ba);
			c = t.getTarget("CELLAR", ba);
			ba = a.getBuildingAttribute("FLOWER-RATIO", metric);
			g = t.getTarget("GARDEN", ba);
		} catch (AttributeDuplicateException | WrongAttributeException | UnknownTargetException | UnknownAttributeException | ConversionMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			generateXmlFromTargets(g, f, c);
		} catch (WrongMappingFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  /**
	   * Handles toolchain execution until the converter tool. The output will be in the temporary folder.
	   * Also parses the data from the output.
	   * 
	   * @param outout The {@link PrintStream} instance that will be set for each executor, so we can read their outputs and
	   *          display them for the user.
	   * @throws ExecutionException if any exception occurs during execution.
	   */
	  public void prepare(PrintStream out) throws ExecutionException {
		  try {
			  if(this.tempFolder == null){
				  this.tempFolder = createTempFolder();
			  }
			  
		      new MetricGeneratorExecutor().execute(tempFolder, executionOptions, out);
		      new ConverterToolExecutor().execute(tempFolder, executionOptions, out);
		      
		  } catch (Exception e) {
			  throw new ExecutionException("Toolchain execution failed!", e);
		  }
		  
		  parseMetricsFromTempFile();
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
	private void parseMetricsFromTempFile(){
		
		metrics = new HashSet<>();
		
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
			MetricDataType dataType = MetricDataType.getMetricDataType(property.getAttribute("type").toUpperCase());
			item.setDataType(dataType);
			metrics.add(item);
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void generateXmlFromTargets(BuildingTargettable garden, BuildingTargettable floor, BuildingTargettable cellar) throws WrongMappingFormatException {
		if (!GuiUtils.areAllDistinct(garden.getName(), floor.getName(), cellar.getName())) {
			// if any of the attribute names equal, the xml cannot be created due to wrong format
			System.out.println(garden.getName() + floor.getName() + cellar.getName());
			throw new WrongMappingFormatException("You cannot have duplicates of a target.");
		}
		Document doc;
		try {
			doc = DocumentBuilderFactory.newInstance()
				      .newDocumentBuilder().newDocument();
			Element mapping = doc.createElement("mapping");
		    doc.appendChild(mapping);
		    mapping.setAttribute("version", "2.0");
		    
		    for(BuildingTargettable target : new BuildingTargettable[]{garden, floor, cellar}) {
		    	Element targetElement = doc.createElement("linking");
		    	mapping.appendChild(targetElement);
		    	targetElement.setAttribute("source", target.getSource());
		    	targetElement.setAttribute("target", target.getName());
		    	for(BuildingAttribute ba : target.getAttributes()) {
		    		Element attrElement = doc.createElement("binding");
		    		targetElement.appendChild(attrElement);
		    		attrElement.setAttribute("from", ba.getMetricType().getName());
		    		attrElement.setAttribute("to", ba.getName());
		    	}
		    }
		    Transformer serializer = TransformerFactory.newInstance().newTransformer();
		    serializer.setOutputProperty("omit-xml-declaration", "yes");
		    serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		    serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		    serializer.transform(new DOMSource(doc), new StreamResult(System.out));
		}catch (ParserConfigurationException | TransformerException | TransformerFactoryConfigurationError e) {
			
		}
	}
	
	

}
