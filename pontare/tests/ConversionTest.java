package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ConversionTest {

	@Test
	void test() {
		int  x = 10;
		
		Object xTransf = (Object) x;
		
		assertEquals(x, xTransf);
	}

}
