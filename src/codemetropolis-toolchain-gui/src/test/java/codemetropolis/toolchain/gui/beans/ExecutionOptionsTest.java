/**
 * 
 */
package codemetropolis.toolchain.gui.beans;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import codemetropolis.toolchain.converter.control.ConverterType;
import codemetropolis.toolchain.placing.layout.LayoutAlgorithm;
import junit.framework.Assert;

/**
 * @author Aliz Kiraly
 *
 */
@SuppressWarnings("deprecation")
public class ExecutionOptionsTest {
	ExecutionOptions instance = new ExecutionOptions();
	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#getProjectName()}.
	 */
	@Test
	public void testGetProjectName() {
		instance.projectName = "nev";
		String str = instance.getProjectName();
		assertEquals(str, instance.projectName);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#getConverterType()}.
	 */
	@Test
	public void testGetConverterType() {
		instance.converterType = ConverterType.SOURCEMETER;
		ConverterType ct = instance.getConverterType();
		assertEquals(ct, instance.converterType);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#getScale()}.
	 */
	@Test
	public void testGetScale() {
		instance.scale = 1.0f;
		float flt = instance.getScale();
		Assert.assertEquals(flt, instance.scale, 0.0f);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#isValidate()}.
	 */
	@Test
	public void testIsValidate() {
		instance.validate = true;
		boolean val = instance.isValidate();
		assertEquals(val, instance.validate);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#getLayoutAlgorithm()}.
	 */
	@Test
	public void testGetLayoutAlgorithm() {
		instance.layoutAlgorithm = LayoutAlgorithm.PACK;
		LayoutAlgorithm la = instance.getLayoutAlgorithm();
		assertEquals(la, instance.layoutAlgorithm);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#isShowMap()}.
	 */
	@Test
	public void testIsShowMap() {
		instance.showMap = true;
		boolean val = instance.isShowMap();
		assertEquals(val, instance.showMap);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#getMinecraftRoot()}.
	 */
	@Test
	public void testGetMinecraftRoot() {
		instance.minecraftRoot = new File("test.exe");
		File file = instance.getMinecraftRoot();
		assertEquals(file, instance.minecraftRoot);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#setProjectName(java.lang.String)}.
	 */
	@Test
	public void testSetProjectName() {
		instance.setProjectName("Test");
		String pn = "Test";
		assertEquals(pn, instance.projectName);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#setConverterType(codemetropolis.toolchain.converter.control.ConverterType)}.
	 */
	@Test
	public void testSetConverterType() {
		instance.setConverterType(ConverterType.SOURCEMETER);
		ConverterType cv = ConverterType.SOURCEMETER;
		assertEquals(cv, instance.converterType);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#setScale(float)}.
	 */
	@Test
	public void testSetScale() {
		instance.setScale(1.0f);
		float sc = 1.0f;
		Assert.assertEquals(sc, instance.scale, 0.0f);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#setValidate(boolean)}.
	 */
	@Test
	public void testSetValidate() {
		instance.setValidate(true);
		boolean val = true;
		assertEquals(val, instance.validate);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#setLayoutAlgorithm(codemetropolis.toolchain.placing.layout.LayoutAlgorithm)}.
	 */
	@Test
	public void testSetLayoutAlgorithm() {
		instance.setLayoutAlgorithm(LayoutAlgorithm.PACK);
		LayoutAlgorithm la = LayoutAlgorithm.PACK;
		assertEquals(la, instance.layoutAlgorithm);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#setShowMap(boolean)}.
	 */
	@Test
	public void testSetShowMap() {
		instance.setShowMap(true);
		boolean sm = true;
		assertEquals(sm, instance.showMap);
	}

	/**
	 * Test method for {@link codemetropolis.toolchain.gui.beans.ExecutionOptions#setMinecraftRoot(java.io.File)}.
	 */
	@Test
	public void testSetMinecraftRoot() {
		instance.setMinecraftRoot(new File("test.exe"));
		File file = new File("test.exe");
		assertEquals(file, instance.minecraftRoot);
	}

}
