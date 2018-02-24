package codemetropolis.toolchain.gui;

import javax.swing.filechooser.FileFilter;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link FileFilter} implementation for only showing directories and executables.
 *
 * @author Aliz Kiraly {@literal <KIAWABT.SZE>}
 */

public class AutoCompletionTest {

	private AutoCompletion instance = new AutoCompletion();
	
	/**
	 * Tests StartWithIgnoreCase method
	 */
	@Test
	public void testStartsWithIgnoreCase1() {
		String str1 = "MAcska";
		String str2 = "macs";
		boolean result = instance.startsWithIgnoreCase(str1,str2);
		Assert.assertEquals(result, true);
	}
	
	@Test
	public void testStartsWithIgnoreCase2() {
		String str1 = "macska";
		String str2 = "kecske";
		boolean result = instance.startsWithIgnoreCase(str1,str2);
		Assert.assertEquals(result, false);
	}

}
