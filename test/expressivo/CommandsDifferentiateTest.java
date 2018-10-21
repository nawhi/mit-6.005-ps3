/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandsDifferentiateTest {

	@Test(expected=IllegalArgumentException.class)
	public void throwsIfEmptyExpression() {
		Commands.differentiate("", "x");
	}

	@Test(expected=IllegalArgumentException.class)
	public void throwsIfEmptyVariable() {
		Commands.differentiate("x", "");
	}

	@Test(expected=IllegalArgumentException.class)
	public void throwsIfInvalidExpression() {
		Commands.differentiate("(", "x");
	}

	@Test(expected=IllegalArgumentException.class)
	public void throwsIfInvalidVariable() {
		Commands.differentiate("x+2", "1");
	}
	
    /*
     * Where x is our target variable...
     * Product(v, v) -> Product(2, v)
     * Product(v, other) or Product(other, v) -> other
     * 
     *  Sum(v, other) -> 1
     *  Product(v, other) -> other
     *  Product(v, v) -> Product(2, v)
     *  Anything else -> 0
     * 
     * How to handle brackets? 
     */
	
	@Test
	public void singleNumericReturnsZero() {
		assertEquals("0", Commands.differentiate("1", "x"));
	}
	
	@Test
	public void singleNonTargetVariableReturnsZero() {
		assertEquals("0", Commands.differentiate("y", "x"));
	}
	
	@Test
	public void singleTargetVariableReturnsOne() {
		assertEquals("1", Commands.differentiate("x", "x"));
	}
	
	@Test
	public void simpleNumericSumReturnsZero() {
		assertEquals("0", Commands.differentiate("1+2", "x"));
	}
	
	@Test
	public void complexNumericSumReturnsZero() {
		assertEquals("0", Commands.differentiate("1+2+3+4+5.5", "x"));
	}
	
}
