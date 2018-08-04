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
		
		assertEquals("0", Expression.parse("0").toString());
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
		invalid("1.2.3");
		invalid(".2.");		
		invalid("f1.3");
		invalid(".");
		
		invalid("-3.5"); // For now
		invalid("2,345,678"); // For now
	}
	
	@Test
	public void testInvalidVariables() {
		invalid("2a");
		invalid("2.5a");
	}
		
	@Test
	public void testVariables() {
		Expression var = Expression.parse("myvar");
		assertThat(var, instanceOf(Variable.class));
		assertEquals("myvar", var.toString());
		
		Expression var2 = Expression.parse("_123");
		assertThat(var2, instanceOf(Variable.class));
		assertEquals("_123", var2.toString());
		
		assertEquals("_foo", Expression.parse("_foo").toString());
		assertEquals("_f1", Expression.parse("_f1").toString());
		assertEquals("_1f", Expression.parse("_1f").toString());
	}
	
	@Test
	public void testSimpleSums() {
		Expression sum = Expression.parse("a+b");
		assertThat(sum, instanceOf(Sum.class));
		assertEquals(new Sum(new Variable("a"), new Variable("b")), sum);
		
		assertEquals("x+3", Expression.parse("x+3").toString());
		assertEquals("3+x", Expression.parse("3+x").toString());
		assertEquals("0.9+3.356", Expression.parse(".9+3.356").toString());
	}
	
	@Test
	public void testChainedSums() {
		assertEquals("x+y+z", Expression.parse("x+y+z").toString());
		assertEquals("1.2+3.5+f+98+2345+0.3",
				Expression.parse("1.2 + 3.5 + f + 98 + 2345 + .3").toString());
	}
	
	@Test
	public void testSimpleProducts() {
		Expression prod = Expression.parse("a*b");
		assertThat(prod, instanceOf(Product.class));
		assertEquals(new Product(new Variable("a"), new Variable("b")), prod);
		
		assertEquals("x*3", Expression.parse("x*3").toString());
		assertEquals("3*x", Expression.parse("3*x").toString());
		assertEquals("0.9*3.356", Expression.parse(".9*3.356").toString());
	}
	
	@Test
	public void testChainedProducts() {
		assertEquals("x*y*z", Expression.parse("x*y*z").toString());
		assertEquals("1.2*3.5*f*98*2345*0.3",
				Expression.parse("1.2 * 3.5 * f * 98 * 2345 * .3").toString());
	}
	
	@Test
	public void testMixedSumsAndProductsWithoutParens() {
		assertEquals("x+y*z", Expression.parse("x+y*z").toString());
		assertEquals("a*b+c", Expression.parse("a*b+c").toString());
		assertEquals("1+2*3+4*5", Expression.parse("1+2*3+4*5").toString());
		assertEquals("x + y + 2.4*3.5*c", Expression.parse("x+y+2.4*3.5*c").toString());
	}
	
	@Test
	public void testMixedSumsAndProductsWithParens() {
		assertEquals("(x+y)*z", Expression.parse("(x+y)*z").toString());
		assertEquals("x*(y+z)", Expression.parse("(x+y)*z").toString());
		assertEquals("(2.5+3.6+4.8)*foo", Expression.parse("(2.5+3.6+4.8)*foo").toString());
		assertEquals("x*(y+4+6*(z+w))", Expression.parse("x*(y+4+6*(z+w))").toString());
	}
	
	@Test
	public void testParenSimplification() {
		assertEquals("a+b", Expression.parse("((a+(b)))").toString());
		assertEquals("a*b", Expression.parse("((a*(b)))").toString());
		assertEquals("a+b+c", Expression.parse("(a+b)+c)").toString());
		assertEquals("a*b*c", Expression.parse("((a)*b)*c").toString());
		assertEquals("(a+b)*c", Expression.parse("((a)+(b))*c").toString());
	}
	
	@Test
	public void testInvalidParens() {
		invalid("(3+a");
		invalid("3+)a");
		invalid("3)");
	}
	
	@Test
	public void testComplex() {
		// Any cases that fail in general use to go here
		fail("Todo");
	}
	
	private void invalid(String s) {
		try {
			Expression.parse(s);
			fail("Attempt to parse " + s + " should fail");
		} catch (IllegalArgumentException ex) {
			return;
		}
		
	}


}
