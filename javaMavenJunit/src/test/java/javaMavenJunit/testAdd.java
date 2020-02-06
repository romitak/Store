package javaMavenJunit;

import static org.junit.Assert.*;

import org.junit.Test;

public class testAdd {

	@Test
	public void test() {
		//fail("Not yet implemented");
		Add ad = new Add();
		int output = ad.addNumbers(4, 7);
		assertEquals(11,output);
	}

}
