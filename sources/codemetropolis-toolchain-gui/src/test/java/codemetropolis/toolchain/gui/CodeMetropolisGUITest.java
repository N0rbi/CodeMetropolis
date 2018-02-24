package codemetropolis.toolchain.gui;

import static org.junit.Assert.*;
import org.junit.Test;
import codemetropolis.toolchain.gui.beans.ExecutionOptions;

/**
 * @author Aliz Kiraly
 *
 */

public class CodeMetropolisGUITest {
	GUIController controller = new GUIController();
	CodeMetropolisGUI instance = new CodeMetropolisGUI(controller);

	/**
	 * Tests ValidateProjectName method
	 */
	@Test
	public void testValidateProjectName1() {
		ExecutionOptions eo = new ExecutionOptions();
		eo.setProjectName("test");
		assertEquals(instance.validateProjectName(eo), true);
	}
	
	@Test
	public void testValidateProjectName2() {
		ExecutionOptions eo = new ExecutionOptions();
		eo.setProjectName("");
		assertEquals(instance.validateProjectName(eo), false);
	}

}
