/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {
	
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

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
	
	@Test public void testVariablesAlone() {
		Variable v = new Variable("foo");
		assertEquals("Variable's string representation should be correct", 
				v.toString(), "foo");
		assertTrue("Variables with the same ident should equals() each other", 
				new Variable("foo").equals(new Variable("foo")));
	}
	
	@Test public void testNumbersAlone() {
		assertEquals("Int's string representation should be correct",
				new Number("1").toString(), "1");
		assertEquals("Strings of ints with the same value should equal each other",
				new Number("1").toString(), new Number("1").toString());
		
		assertEquals("Float string should be correct", 
				new Number("1.05").toString(), "1.05");
		assertEquals("Float string shouldn't have trailing zeroes",
				new Number("1.050").toString(), "1.05");
		assertEquals("Strings of floats with the same value should equal each otehr",
				new Number("1.05").toString(), new Number("1.05").toString());
	}
	
	@Test public void testNumberEqualityBetweenFloatAndInt() {
		assertEquals("Int's string should equal a whole-number float's string", 
				new Number("1").toString(), new Number("1.00").toString());
		assertEquals("Int Number should equal a whole-number float Number",
				new Number("1"), new Number("1.00"));
	}
	
	@Test public void testNumberLimits() {
		assertEquals("Numbers > INT_MAX should work correctly", 
				new Number("2147483648").toString(), "2147483648");
		assertEquals("Numbers with lots of decimal places should work correctly",
				new Number("1.61803398874989484").toString(), "1.61803398874989484"); 
	}
	
}
