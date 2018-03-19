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
				"foo", v.toString());
		assertTrue("Variables with the same ident should equals() each other", 
				new Variable("foo").equals(new Variable("foo")));
	}
	
	@Test public void testNumbersAlone() {
		Number i = new Number("1");
		assertEquals("Int's string representation should be correct",
				i.toString(), "1");
		assertEquals("Strings of ints with the same value should equal each other",
				i.toString(), new Number("1").toString());
		
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
				"1.61803398874989484", new Number("1.61803398874989484").toString()); 
	}
	
	@Test public void testSum() {
		assertEquals("Identical sums should equal each other",
				new Sum(new Number("1"), new Variable("foo")),
				new Sum(new Number("1"), new Variable("foo")));
		assertEquals("Identical sums' string representations should equal each other",
				new Sum(new Variable("foo"), new Variable("bar")).toString(),
				new Sum(new Variable("foo"), new Variable("bar")).toString());
		Sum s1 = new Sum(new Number("1"), new Number("2"));
		Sum s2 = new Sum(new Number("3"), new Number("4"));
		assertEquals("Sums should commute without arbitrary parentheses", 
				"1+2+3+4", new Sum(s1, s2).toString());
	}
	
	@Test public void testProduct() {
		assertEquals("Identical products should equal each other",
				new Product(new Number("1"), new Variable("foo")),
				new Product(new Number("1"), new Variable("foo")));
		assertEquals("Identical products' string representations should equal each other",
				new Product(new Variable("foo"), new Variable("bar")).toString(),
				new Product(new Variable("foo"), new Variable("bar")).toString());
		Product p1 = new Product(new Number("1"), new Number("2"));
		Product p2 = new Product(new Number("3"), new Number("4"));
		assertEquals("Products should commute without arbitrary parentheses",
				"1*2*3*4", new Product(p1, p2).toString());
	}
	
	@Test public void testBinopStructuralEquality() {
		Variable a = new Variable("a");
		Variable b = new Variable("b");
		assertNotEquals("Sums with swapped lvalue/rvalue should not compare equal",
				new Sum(a, b), new Sum(b, a));
		assertNotEquals("Products with swapped lvalue/rvalue should not compare equal",
				new Product(a, b), new Product(b, a)); 
	}
	
	@Test public void testBinopObservationalEquality() {
		throw new AssertionError("TODO");
	}
	
	@Test public void testExpressionAddsParenthesesWhenNeeded() {
		Variable a = new Variable("a");
		Expression multFirst = new Sum(new Product(a,a), new Number("1"));
		Expression addFirst = new Product(new Sum(a, new Number("1")), a);
				
		assertEquals("Shouldn't add parens unless necessary", "a*a+1", multFirst);
		assertEquals("Should add parens to keep BIDMAS order", "a*(a+1)", addFirst);
		assertFalse("Non-commutative expressions in different order shouldn't be equal",
				multFirst.equals(addFirst));
		
		// These also check for any aliasing bugs by passing in the same object twice
		assertEquals("Shouldn't add parens unless necessary in complex subexpressions",
				"a*(a+1)*a*(a+1)", new Product(addFirst, addFirst));
		assertEquals("Should add parens to keep BIDMAS in complex subexpressions", 
				"(a*a+1)*(a*a+1)", new Product(multFirst, multFirst));

	}
	
	
}
