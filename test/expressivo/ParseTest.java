package expressivo;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.junit.Test;

public class ParseTest {

	@Test
	public void testBasics() {
		Expression i = Expression.parse("1");
		assertThat(i, instanceOf(Numeric.class));
		assertEquals("1", i.toString());
		
		Expression f = Expression.parse("1.5");
		assertThat(f, instanceOf(Numeric.class));
		assertEquals("1.5", f.toString());
		
		Expression x = Expression.parse("x");
		assertThat(x, instanceOf(Variable.class));
		assertEquals("x", x.toString());
		
	}
	
	@Test
	public void testIntegers() {
		// Sanity check
		Expression i = Expression.parse("1");
		assertThat(i, instanceOf(Numeric.class));
		assertEquals("1", i.toString());
		
		parsesIdentically("0");
		assertEquals("1", Expression.parse("01").toString());
		assertEquals("10", Expression.parse("10").toString());
		
	}
	
	
	@Test
	public void testFloats() {
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
		assertThat(var, instanceOf(Variable.class));
		assertEquals("myvar", var.toString());
		
		Expression var2 = Expression.parse("_123");
		assertThat(var2, instanceOf(Variable.class));
		assertEquals("_123", var2.toString());
		
		parsesIdentically("_foo");
		parsesIdentically("_f1");
		parsesIdentically("_1f");
	}
	
	@Test
	public void testSimpleSums() {
		Expression sum = Expression.parse("a+b");
		assertThat(sum, instanceOf(Sum.class));
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
		assertThat(prod, instanceOf(Product.class));
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
	
	@Test
	public void testMixedSumsAndProductsWithoutParens() {
		parsesIdentically("x+y*z");
		parsesIdentically("a*b+c");
		parsesIdentically("1+2*3+4*5");
		assertEquals("x+y+2.4*3.5*c", 
				Expression.parse("x + y + 2.4*3.5*c").toString());
	}
	
	@Test
	public void testMixedSumsAndProductsWithParens() {
		parsesIdentically("(x+y)*z");
		parsesIdentically("x*(y+z)");
		parsesIdentically("(2.5+3.6+4.8)*foo");
		parsesIdentically("x*(y+4+6*(z+w))");
	}
	
	@Test
	public void testParenSimplification() {
		parsesIdentically("a+b");
		parsesIdentically("a*b");
		parsesIdentically("a+b+c");
		parsesIdentically("a*b*c");
		parsesIdentically("(a+b)*c");
	}
	
	@Test
	public void testInvalidParens() {
		parseFails("(3+a");
		parseFails("3+)a");
		parseFails("3)");
	}
	
	@Test
	public void testComplex() {
		// Any cases that fail in general use to go here
		fail("Todo");
	}
	
	private void parseFails(String s) {
		try {
			Expression.parse(s);
			fail("Attempt to parse " + s + " should fail");
		} catch (IllegalArgumentException ex) {
			return;
		}
	}
	
	private void parsesIdentically(String s) {
		assertEquals(s, Expression.parse(s).toString());
	}


}
