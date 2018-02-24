package codemetropolis.toolchain.gui;

import static org.junit.Assert.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import codemetropolis.toolchain.gui.beans.ExecutionOptions;
import codemetropolis.toolchain.gui.components.CMMetricPanel;

/**
*
* @author Bettina Csizolszki {@literal <CSBVABT.SZE>}
*
*/


public class GUIControllerTest {
	
	GUIController instance = new GUIController();
	SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS");


	@Test
	public void testGetExecutionOptions() {
		instance.executionOptions = new ExecutionOptions();
		Object obj = instance.getExecutionOptions();
		assertEquals(obj, instance.executionOptions);
	}

	@Test
	public void testGetMetricGeneratorPanels() {
		instance.metricGeneratorPanels = new ArrayList<CMMetricPanel>();
		List<CMMetricPanel> list = instance.getMetricGeneratorPanels();
		assertEquals(list, instance.metricGeneratorPanels);
	}
	
	//@Test
	//public void testGetCurrentDateString(){
		//instance.getCurrentDateString = DATE_FORMATTER.format(new Date());
		//String date = instance.getCurrentDateString();
		//assertEquals(date, instance.getCurrentDateString());
	//}

}
