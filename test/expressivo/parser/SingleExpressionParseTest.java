package expressivo.parser;

import expressivo.expressions.Expression;
import expressivo.expressions.Product;
import expressivo.expressions.Sum;
import expressivo.expressions.Variable;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class SingleExpressionParseTest {

	@Test
	@Parameters({
			"0|0",
			"1|1",
			"01|1",
			"10|10"
	})
	public void integersCanBeParsed(String input, String expectedOutput) {
		assertThat(Expression.parse(input).toString()).isEqualTo(expectedOutput);
	}
	
	@Test
	public void testFloatNumericss() {
		Expression f = Expression.parse("1.5");
		assertEquals("1.5", f.toString());
		
		assertEquals("0.5", Expression.parse(".5").toString());

		// Conversion to int is OK: it'll be converted back internally if needed
		assertEquals("2", Expression.parse("2.0").toString());

		assertEquals("2.3", Expression.parse("2.300").toString());
		assertEquals("3.1415926535", Expression.parse("3.1415926535").toString());
		assertEquals("25.3", Expression.parse("0025.300").toString());
	}
	
	@Test
	public void testInvalidNumerics() {
		parseFails("1.2.3");
		parseFails(".2.");
		parseFails("f1.3");
		parseFails(".");
		
		parseFails("-3.5"); // For now
		parseFails("2,345,678"); // For now
	}
	
	@Test
	public void testInvalidVariables() {
		parseFails("2a");
		parseFails("2.5a");
	}
		
	@Test
	public void testVariables() {
		Expression var = Expression.parse("myvar");
		assertEquals("myvar", var.toString());
		
		Expression var2 = Expression.parse("_123");
		assertEquals("_123", var2.toString());
		
		parsesIdentically("_foo");
		parsesIdentically("_f1");
		parsesIdentically("_1f");
	}
	
	@Test
	public void testSimpleSums() {
		Expression sum = Expression.parse("a+b");
		assertEquals(new Sum(new Variable("a"), new Variable("b")), sum);
		
		parsesIdentically("x+3");
		parsesIdentically("3+x");
		parsesIdentically("0.9+3.356");
	}
	
	@Test
	public void testChainedSums() {
		parsesIdentically("x+y+z");
		assertEquals("1.2+3.5+f+98+2345+0.3",
				Expression.parse("1.2 + 3.5 + f + 98 + 2345 + .3").toString());
	}
	
	@Test
	public void testSimpleProducts() {
		Expression prod = Expression.parse("a*b");
		assertEquals(new Product(new Variable("a"), new Variable("b")), prod);
		
		parsesIdentically("x*3");
		parsesIdentically("3*x");
		parsesIdentically("0.9*3.356");
	}
	
	@Test
	public void testChainedProducts() {
		parsesIdentically("x*y*z");
		assertEquals("1.2*3.5*f*98*2345*0.3",
				Expression.parse("1.2 * 3.5 * f * 98 * 2345 * .3").toString());
	}
	
	private void parseFails(String s) {
		try {
			Expression.parse(s);
			fail("Attempt to parse " + s + " should fail");
		} catch (IllegalArgumentException ex) {
		}
	}
	
	private void parsesIdentically(String s) {
		assertEquals(s, Expression.parse(s).toString());
	}


}
