package codemetropolis.toolchain.gui.mapping;

import org.junit.Assert;
import org.junit.Test;


public class MetricItemTypeTest {

	@Test
	public void testEqualsTrue() {
		MetricItemType mit1 = new MetricItemType();
		MetricDataType mdt = MetricDataType.getMetricDataType("STRING");
		mit1.setDataType(mdt);
		mit1.setName("");
		MetricItemType mit2 = new MetricItemType();
		mit2.setDataType(mdt);
		mit2.setName("");
		
		Assert.assertTrue(mit1.equals(mit2));	
	}
	
	@Test
	public void testEqualsFalse() {
		MetricItemType mit1 = new MetricItemType();
		MetricDataType mdt = MetricDataType.getMetricDataType("STRING");
		mit1.setDataType(mdt);
		mit1.setName("");
		MetricItemType mit2 = new MetricItemType();
		mit2.setDataType(mdt);
		mit2.setName("Foo");
		
		Assert.assertFalse(mit1.equals(mit2));	
	}
	
	@Test
	public void testEqualsInvalid() {
		MetricItemType mit1 = new MetricItemType();
		MetricDataType mdt = MetricDataType.getMetricDataType("STRING");
		mit1.setDataType(mdt);
		mit1.setName("");
		Object obj = ""; 
		
		Assert.assertFalse(mit1.equals(obj));	
	}
}
