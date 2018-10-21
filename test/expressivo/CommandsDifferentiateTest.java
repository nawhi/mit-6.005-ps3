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

	@Test
	public void expressionsWithoutTargetVariableReturnZero() {
		assertEquals("0", Commands.differentiate("5.0", "x"));
		assertEquals("0", Commands.differentiate("3+5*(4+7)", "x"));
		assertEquals("0", Commands.differentiate("a", "x"));
		assertEquals("0", Commands.differentiate("a+3.4+5*b", "x"));
	}

}
