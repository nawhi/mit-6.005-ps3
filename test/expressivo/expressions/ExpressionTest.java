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
}
