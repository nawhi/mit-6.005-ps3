/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo.expressions;

import static org.junit.Assert.*;

import expressivo.expressions.*;
import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {
	
    // Flags for additional equality tests
	private static final int IGNORE_WS = 0x1;
    private static final int TEST_HC   = 0x2;
    private static final int ALL_FLAGS = IGNORE_WS & TEST_HC;

	/*
	 * Testing strategy:
	 * - Binop & Primitive:
	 *    - an object ported to string and back should be equal to itself
	 * - Binop:
	 *    - check they work with Variables, Numbers, and one of each
	 *    - check combinations of each other work
	 * - Structural equality:
	 *    - Primitive(a) == Primitive(a)
	 *    - Binop(a, b) == Binop(a, b)
	 *    - Binop(a, b) != Binop(b, a)
	 *    - TODO Binop(a, Binop(b, c)) vs Binop(Binop(a, b), c) ???
	 *    - Number(1) == Number(1.0000)
	 * - Grouping
	 *    - TODO ???
	 * - Hashcode:
	 *    - equals() will work by comparing hashcodes, so the structural
	 *      equality tests shoud cover this
	 */

	@Test public void testExpressionAddsParenthesesWhenNeeded() {
		Variable a = new Variable("a");
		Expression multFirst = new Sum(new Product(a,a), new Numeric("1"));
		Expression addFirst = new Product(new Sum(a, new Numeric("1")), a);
				
		assertEquals("Shouldn't add parens unless necessary", "a*a+1", multFirst.toString(), IGNORE_WS);
		assertEquals("Should add parens to keep BIDMAS order", "(a+1)*a", addFirst.toString(), IGNORE_WS);
		assertNotEquals("Non-commutative expressions in different order shouldn't be equal", multFirst, addFirst);
		
		Variable b = new Variable("b");
		Expression aabb = new Sum(new Sum(a,a), new Sum(b,b)); 
		assertEquals("Should keep BIDMAS order (longer sum)", 
				"(a+a+b+b)*2", new Product(aabb, new Numeric("2")).toString());
		
		// These also check for any aliasing bugs by passing in the same object twice
		assertEquals("Shouldn't add parens unless necessary in complex subexpressions",
				"(a+1)*a*(a+1)*a", new Product(addFirst, addFirst).toString(), IGNORE_WS);
		assertEquals("Should add parens to keep BIDMAS in complex subexpressions", 
				"(a*a+1)*(a*a+1)", new Product(multFirst, multFirst).toString(), IGNORE_WS);

	}
	
	@Test public void testComplexExpressions() {
		Variable w = new Variable("w");
		Variable x = new Variable("x");
		Variable y = new Variable("y");
		Variable z = new Variable("z");
		Numeric five = new Numeric("5");
		Numeric pi = new Numeric("3.1415927");
		
		assertEquals("Complex expression 1", 
				"(x+y)*5*(x+3.1415927)", 
				new Product(
						new Product(new Sum(x,y),five), 
						new Sum(x, pi)
					).toString());
		
		assertEquals("Complex expression 2",
				"(x+y)*(5*(x+y)+3.1415927*(x+5))",
				new Product(
						new Sum(x,y), 
						new Sum(
								new Product(five, new Sum(x,y)),
								new Product(pi, new Sum(x,five))
						)
					).toString());
		
		Sum xplusy = new Sum(x, y);
		Sum yplusx = new Sum(y, x);
		Sum zplusw = new Sum(z, w);
		Sum wplusz = new Sum(w, z);
		Sum xplusyplusz = new Sum(new Sum(x, y), z);
		
		assertEquals("Complex expression 3",
				"((x+y)*(x+y)+(z+w)*(z+w))*((w+z)*(w+z)+(y+x)*(y+x))",
				new Product(new Sum(pow(xplusy, 2), pow(zplusw, 2)),
							new Sum(pow(wplusz, 2), pow(yplusx, 2))).toString());
		
		assertEquals("Complex expression 4",
				"(x+y+z)*(x+y+z)*(x+y+z)",
				pow(xplusyplusz, 3).toString());
	}
	
	private Expression pow(Expression base, int exp) {
		Expression ret = base;
	    for (int i = 1; i < exp; i++) {
			ret = new Product(ret, base);
		}
	    return ret;
	}
	
	/*
	 * Whitespace does not matter in tests here, so ignore it in string
	 * equality tests
	 */
    private void assertEquals(String msg, Object expected, Object actual, int flags) {
		if (expected instanceof String 
				&& actual instanceof String
				&& (flags & IGNORE_WS) != 0) {
			String o1 = ((String) expected).replaceAll("\\s", "");
			String o2 = ((String) actual).replaceAll("\\s", "");
			Assert.assertEquals(msg, o1, o2);
		} else {
			Assert.assertEquals(msg, expected, actual);
		}
		if ((flags & TEST_HC) != 0) {
			Assert.assertEquals(msg, expected.hashCode(), actual.hashCode());
		}
	}
	
	private void assertEquals(String msg, Object expected, Object actual) {
		Assert.assertEquals(msg, expected, actual);
	}
	
}
