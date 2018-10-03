package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParenthesisParseTest {

	@Test
	public void test() {
		fail("Not yet implemented");
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
