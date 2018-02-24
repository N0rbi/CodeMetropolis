package codemetropolis.toolchain.gui.mapping.targettable;

import org.junit.Assert;
import org.junit.Test;


public class BuildingTargettableTest {
	
	@Test
	public void testValidBuildingTargettable () {
		TargetFactory af = new TargetFactory();
		BuildingTargettable test = null;
		try {
			test = af.getTarget("CELLAR");
		} catch (Exception e) {
			
		}
		
		Assert.assertTrue(test instanceof CellarTarget);
	}
	
	@Test
	public void testInvalidBuildingTargettable () {
		TargetFactory af = new TargetFactory();
		BuildingTargettable test = null;
		boolean thrown = false;
		try {
			test = af.getTarget("");
		} catch (Exception e) {
			thrown = true;
		}
		Assert.assertNull(test);
		Assert.assertTrue(thrown);
	}

}
