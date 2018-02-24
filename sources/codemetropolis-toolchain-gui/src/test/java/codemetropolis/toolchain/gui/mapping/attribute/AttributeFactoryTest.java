package codemetropolis.toolchain.gui.mapping.attribute;

import org.junit.Assert;
import org.junit.Test;

public class AttributeFactoryTest {
	
	@Test
	public void testValidBuildingAttribute() {
		AttributeFactory af = new AttributeFactory();
		BuildingAttribute test = null;
		try {
			test = af.getBuildingAttribute("WIDTH");
		} catch (Exception e) {
			
		}
		
		Assert.assertTrue(test instanceof WidthAttribute);
	}
	
	@Test
	public void testInvalidBuildingAttribute() {
		AttributeFactory af = new AttributeFactory();
		BuildingAttribute test = null;
		boolean thrown = false;
		try {
			test = af.getBuildingAttribute("");
		} catch (Exception e) {
			thrown = true;
		}
		Assert.assertNotNull(test);
		Assert.assertFalse(thrown);
		
	}

}
