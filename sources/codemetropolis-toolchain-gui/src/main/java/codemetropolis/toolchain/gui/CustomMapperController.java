package codemetropolis.toolchain.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import codemetropolis.toolchain.gui.beans.ExecutionOptions;
import codemetropolis.toolchain.gui.utils.GuiUtils;

public class CustomMapperController {
	
	public static String[] filterMetrics = new String[] {"source_id", "Name", "LongName"};
	
	private ExecutionOptions executionOptions;
	
	public CustomMapperController(ExecutionOptions executionOptions) {
		this.executionOptions = executionOptions;
	}
	
	
	public List<String> parseMetricsFromTempFile(){
		
		HashSet<String> metrics = new HashSet<>();
		
		try {
		File converterResult = new File(GuiUtils.getTempFolder(executionOptions), "converter-results.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(converterResult);
		
		NodeList properties = doc.getElementsByTagName("property");
		
		for (int i=0; i<properties.getLength(); i++) {
			Element property = (Element) properties.item(i);
			
			metrics.add(property.getAttribute("name"));
		}
		
		metrics.removeAll(Arrays.asList(filterMetrics));
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>(metrics);
	}

}
