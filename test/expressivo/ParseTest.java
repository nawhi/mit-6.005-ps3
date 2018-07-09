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
	public void testLongVariables() {
		fail("not implemented");
	}
	
	@Test
	public void testSums() {
		fail("not implemented");
	}
	
	@Test
	public void testProducts() {
		fail("not implemented");
	}
	
	@Test
	public void testParens() {
		fail("not implemented");
	}

}
