package codemetropolis.toolchain.gui;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;


/**
 * 
 *
 * @author Bettina Csizolszki {@literal <CSBVABT.SZE>}
 */


public class CustomMapperGUITest {


	private static final int COVER_IMAGE_COUNT = 4;
	private static final Random rng = new Random();
	private String[] actual = {"b", "a", "c"};
	
	
	@Test
	public void testRandomGenerator(){
		int number =  rng.nextInt(COVER_IMAGE_COUNT) + 1;
		assertTrue(number < 4 && number > 0);
	}

	@Test
	public void testSortMetrics() {
		String expected[] = {"a", "b", "c"};
		 Arrays.sort(actual);
		 actual.equals(expected);
	}
	
	@Test
	  public void testAcceptXmlFile() {
				File file = new File("test", ".xml");
				assertTrue(file.getName().endsWith(".xml"));
		  }
	@Test
	  public void testRejectNottXmlFile() {
				File file = new File("test", ".png");
				assertFalse(file.getName().endsWith(".xml"));
		  }
	
	@Test
	 public void testAcceptPictureFile() {
			File file = new File("test", ".png");
			assertTrue(file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".bmp"));
	  }
	
	@Test
	 public void testRejectNotPictureFile() {
			File file = new File("test", ".exe");
			assertFalse(file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".bmp"));
	  }
}
