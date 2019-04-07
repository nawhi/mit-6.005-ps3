package expressivo.parser;

import expressivo.Expression;
import org.junit.Assert;
import org.junit.Test;

public class MixedExpressionParseTest {

	@Test
	public void testMixedSumsAndProducts() {
		parsesIdentically("x+y*z");
		parsesIdentically("x*y+z");
		parsesIdentically("a+b*c+d");
		parsesIdentically("a*b+c*d");
		parsesIdentically("a*b*c+d*e*f");
		parsesIdentically("a+b+c*d+e+f");
	}
	
	private void parsesIdentically(String s) {
		Assert.assertEquals(s, Expression.parse(s).toString());
	}
	
}
