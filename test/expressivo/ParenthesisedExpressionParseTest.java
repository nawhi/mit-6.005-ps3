package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParenthesisedExpressionParseTest {

	@Test
	public void testUnnecessaryParenthesesInPrimitives() {
		assertEquals("a", Expression.parse("(a)").toString());
		assertEquals("a", Expression.parse("((a))").toString());
		assertEquals("1", Expression.parse("(1)").toString());
	}
	
	@Test
	public void testSpuriousParenthesesInBinops() {
		assertEquals("a+b", Expression.parse("(a)+(b)").toString());
		assertEquals("a+b", Expression.parse("(a+b)").toString());
		assertEquals("a*b", Expression.parse("(a)*(b)").toString());
		assertEquals("a*b", Expression.parse("(a*b)").toString());
	}
	
	@Test
	public void testSpuriousParenthesesInMixedExpressions() {
		assertEquals("2*a+1", Expression.parse("(2*a) + 1").toString());
	}
	
	@Test
	public void testNecessaryParentheses() {
		assertEquals("2*(a+1)", Expression.parse("2 * (a+1)").toString());
		assertEquals("(b+c)*(d+e)", Expression.parse("(b + c) * (d + e)").toString());
	}
	
}
