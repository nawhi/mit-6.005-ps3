package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

public class MixedExpressionParseTest {

	@Test
	public void testMixedSumsAndProducts() {
		parsesIdentically("x+y*z");
	}
	
	private void parsesIdentically(String s) {
		assertEquals(s, Expression.parse(s).toString());
	}
	
}
