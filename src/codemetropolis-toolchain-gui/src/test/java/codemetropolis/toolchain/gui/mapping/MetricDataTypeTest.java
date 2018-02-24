package codemetropolis.toolchain.gui.mapping;

import org.junit.Assert;
import org.junit.Test;

public class MetricDataTypeTest {

	@Test
	public void testIntegerDataType() {
		MetricDataType mdt = MetricDataType.getMetricDataType("INTEGER");
		Assert.assertEquals(mdt.getName(), "integer");
	}
	
	@Test
	public void testFloatDataType() {
		MetricDataType mdt = MetricDataType.getMetricDataType("FLOAT");
		Assert.assertEquals(mdt.getName(), "float");
	}
	
	@Test
	public void testStringDataType() {
		MetricDataType mdt = MetricDataType.getMetricDataType("STRING");
		Assert.assertEquals(mdt.getName(), "string");
	}
	
	@Test
	public void testInvalidDataType() {
		MetricDataType mdt = MetricDataType.getMetricDataType("");
		Assert.assertNull(mdt);
	}
	
	@Test
	public void testEqualsTrue() {
		MetricDataType mdt1 = MetricDataType.getMetricDataType("INTEGER");
		MetricDataType mdt2 = MetricDataType.getMetricDataType("INTEGER");
		Assert.assertTrue(mdt1.equals(mdt2));
	}
	
	@Test
	public void testEqualsFalse() {
		MetricDataType mdt1 = MetricDataType.getMetricDataType("FLOAT");
		MetricDataType mdt2 = MetricDataType.getMetricDataType("INTEGER");
		Assert.assertFalse(mdt1.equals(mdt2));
	}
	
	@Test
	public void testEqualsNonMetricType() {
		MetricDataType mdt1 = MetricDataType.getMetricDataType("FLOAT");
		Object obj = "Foo";
		
		Assert.assertFalse(mdt1.equals(obj));
	}
	
}
