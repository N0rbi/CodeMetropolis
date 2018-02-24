package codemetropolis.toolchain.gui;

import java.io.File;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.HashMap;
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
	
	public static final String[] filterMetrics = new String[] {"source_id", "Name", "LongName"};
	private File tempFolder;
	private HashMap<String, BuildingAttribute> takenMetrics = new HashMap<>();
	private HashMap<String, BuildingTargettable> takenSources = new HashMap<>();
	
	
	private ExecutionOptions executionOptions;
	private HashSet<MetricItemType> metrics;
	private HashSet<String> sources;
	
	private BuildingTargettable garden;
	private BuildingTargettable floor;
	private BuildingTargettable cellar;
	
	public CustomMapperController(ExecutionOptions executionOptions) {
		this.executionOptions = executionOptions;
		
		TargetFactory f = new TargetFactory();
		
		try{
		garden = f.getTarget("GARDEN");
		floor = f.getTarget("FLOOR");
		cellar = f.getTarget("CELLAR");
		} catch (UnknownTargetException e){
			e.printStackTrace();
			System.exit(-1);
		}
		
		AttributeFactory a = new AttributeFactory();
		
		try{
			//attributes for the garden
			BuildingAttribute flower = a.getBuildingAttribute("FLOWER-RATIO");
			BuildingAttribute tree = a.getBuildingAttribute("TREE-RATIO");
			BuildingAttribute mushroom = a.getBuildingAttribute("MUSHROOM-RATIO");
			
			garden.addBuildingAttribute(flower);
			garden.addBuildingAttribute(tree);
			garden.addBuildingAttribute(mushroom);
			
			//attributes for the floors
			BuildingAttribute fWidth = a.getBuildingAttribute("WIDTH");
			BuildingAttribute fHeight = a.getBuildingAttribute("HEIGHT");
			BuildingAttribute fLength = a.getBuildingAttribute("LENGTH");
			BuildingAttribute fCharacter = a.getBuildingAttribute("CHARACTER");
			BuildingAttribute fExtCharacter = a.getBuildingAttribute("EXTERNAL_CHARACTER");
			BuildingAttribute fTorches = a.getBuildingAttribute("TORCHES");
			
			floor.addBuildingAttribute(fWidth);
			floor.addBuildingAttribute(fHeight);
			floor.addBuildingAttribute(fLength);
			floor.addBuildingAttribute(fCharacter);
			floor.addBuildingAttribute(fExtCharacter);
			floor.addBuildingAttribute(fTorches);

			//attributes for the cellar
			BuildingAttribute cWidth = a.getBuildingAttribute("WIDTH");
			BuildingAttribute cHeight = a.getBuildingAttribute("HEIGHT");
			BuildingAttribute cLength = a.getBuildingAttribute("LENGTH");
			BuildingAttribute cCharacter = a.getBuildingAttribute("CHARACTER");
			BuildingAttribute cExtCharacter = a.getBuildingAttribute("EXTERNAL_CHARACTER");
			BuildingAttribute cTorches = a.getBuildingAttribute("TORCHES");
			
			cellar.addBuildingAttribute(cWidth);
			cellar.addBuildingAttribute(cHeight);
			cellar.addBuildingAttribute(cLength);
			cellar.addBuildingAttribute(cCharacter);
			cellar.addBuildingAttribute(cExtCharacter);
			cellar.addBuildingAttribute(cTorches);
			
		} catch(AttributeDuplicateException|WrongAttributeException | UnknownAttributeException e){
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
		sources = new HashSet<>();
		
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
			
			NodeList elements = doc.getElementsByTagName("element");
			
			for (int i=0; i<elements.getLength(); i++) {
				Element element = (Element) elements.item(i);
				
				String s = element.getAttribute("type");
				
				sources.add(s);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		metrics.remove(filterMetrics);
	}
	
	public String getOutputXml() throws WrongMappingFormatException {
		for(BuildingTargettable target : new BuildingTargettable[]{garden, floor, cellar}){
			if(target.hasNullFields()){
				throw new WrongMappingFormatException("You must assign all attributes in all all the three layers.");
			}
		}
		String output = "";
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
		    StringWriter writer = new StringWriter();
		    StreamResult result = new StreamResult(writer);
		    serializer.transform(new DOMSource(doc), result);
		    output = writer.toString();
		}catch (ParserConfigurationException | TransformerException | TransformerFactoryConfigurationError e) {
			
		}
		
		return output;
	}
	
	public MetricItemType getMetricByString(String s){
		
		for(MetricItemType m : metrics){
			if(m.getName().equals(s)){
				return m;
			}
		}
		
		return null;
		
	}
	
	public void addOrReplaceMetrics(String metric, BuildingAttribute ba) {
		//NOTE: it only resets the old attribute of the metric, because the conversion is not handled here
		BuildingAttribute old = takenMetrics.get(metric);
		if (old != null) {
			old.removeMetric();
		}
		takenMetrics.put(metric, ba);
	}

	public HashSet<MetricItemType> getMetrics() {
		return metrics;
	}

	public BuildingTargettable getGarden() {
		return garden;
	}

	public BuildingTargettable getFloor() {
		return floor;
	}

	public BuildingTargettable getCellar() {
		return cellar;
	}

	public String[] getSources() {
		return sources.toArray(new String[0]);
	}
	
	public void addOrReplaceSources(String source, BuildingTargettable bt) {
		//NOTE: it only resets the old source of the target
		BuildingTargettable old = takenSources.get(source);
		if (old != null) {
			old.setSource(null);
		}
		takenSources.put(source, bt);
	}
}
