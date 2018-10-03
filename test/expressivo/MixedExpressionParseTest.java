package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

public class MixedExpressionParseTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testMixedSumsAndProducts() {
		parsesIdentically("x+y*z");
		parsesIdentically("a*b+c");
		parsesIdentically("1+2*3+4*5");
		assertEquals("x+y+2.4*3.5*c", 
				Expression.parse("x + y + 2.4*3.5*c").toString());
	}
	
	private void parsesIdentically(String s) {
		assertEquals(s, Expression.parse(s).toString());
	}
	
}
