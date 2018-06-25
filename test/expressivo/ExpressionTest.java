/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {
	
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testWhitespaceDoesntMatter() {
    	assertEquals("Whitespace shouldn't matter", "     hello      ", "hello");
    	assertEquals("Whitespace shouldn't matter", "  H  e ll o \t", "\t\t\tHello");
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
		Numeric i = new Numeric("1");
		assertEquals("Int's string representation should be correct",
				i.toString(), "1");
		assertEquals("Strings of ints with the same value should equal each other",
				i.toString(), new Numeric("1").toString());
		
		assertEquals("Float string should be correct", 
				new Numeric("1.05").toString(), "1.05");
		assertEquals("Float string shouldn't have trailing zeroes",
				new Numeric("1.050").toString(), "1.05");
		assertEquals("Strings of floats with the same value should equal each otehr",
				new Numeric("1.05").toString(), new Numeric("1.05").toString());
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class) 
	public void testNumericErrorConditions() {
		Numeric inval = new Numeric("foo");
	}
	
	@Test public void testNumericEqualityBetweenFloatAndInt() {
		assertEquals("Int's string should equal a whole-number float's string", 
				new Numeric("1").toString(), new Numeric("1.00").toString());
		assertEquals("Int Number should equal a whole-number float Number",
				new Numeric("1"), new Numeric("1.00"));
	}
	
	@Test public void testNumberLimits() {
		assertEquals("Numbers > INT_MAX should work correctly", 
				new Numeric("2147483648").toString(), "2147483648");
		assertNotEquals("Numbers that aren't quite equal shouldn't compare as equal",
				 new Numeric("1.0"), new Numeric("1.00000000000000000000000001")); 
	}
	
	@Test public void testSum() {
		assertEquals("Identical sums should equal each other",
				new Sum(new Numeric("1"), new Variable("foo")),
				new Sum(new Numeric("1"), new Variable("foo")));
		assertEquals("Identical sums' string representations should equal each other",
				new Sum(new Variable("foo"), new Variable("bar")).toString(),
				new Sum(new Variable("foo"), new Variable("bar")).toString());
		Sum s1 = new Sum(new Numeric("1"), new Numeric("2"));
		Sum s2 = new Sum(new Numeric("3"), new Numeric("4"));
		assertEquals("Sums should commute without arbitrary parentheses", 
				"1+2+3+4", new Sum(s1, s2).toString());
	}
	
	@Test public void testProduct() {
		assertEquals("Identical products should equal each other",
				new Product(new Numeric("1"), new Variable("foo")),
				new Product(new Numeric("1"), new Variable("foo")));
		assertEquals("Identical products' string representations should equal each other",
				new Product(new Variable("foo"), new Variable("bar")).toString(),
				new Product(new Variable("foo"), new Variable("bar")).toString());
		Product p1 = new Product(new Numeric("1"), new Numeric("2"));
		Product p2 = new Product(new Numeric("3"), new Numeric("4"));
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
		// TODO Binop(a, Binop(b, c)) vs Binop(Binop(a, b), c) ???
		throw new AssertionError("TODO");
	}
	
	@Test public void testExpressionAddsParenthesesWhenNeeded() {
		Variable a = new Variable("a");
		Expression multFirst = new Sum(new Product(a,a), new Numeric("1"));
		Expression addFirst = new Product(new Sum(a, new Numeric("1")), a);
				
		assertEquals("Shouldn't add parens unless necessary", "a*a+1", multFirst.toString());
		assertEquals("Should add parens to keep BIDMAS order", "(a+1)*a", addFirst.toString());
		assertFalse("Non-commutative expressions in different order shouldn't be equal",
				multFirst.equals(addFirst));
		
		// These also check for any aliasing bugs by passing in the same object twice
		assertEquals("Shouldn't add parens unless necessary in complex subexpressions",
				"a*(a+1)*a*(a+1)", new Product(addFirst, addFirst).toString());
		assertEquals("Should add parens to keep BIDMAS in complex subexpressions", 
				"(a*a+1)*(a*a+1)", new Product(multFirst, multFirst).toString());

	}
	
	/*
	 * Whitespace does not matter in tests here, so ignore it in string
	 * equality tests
	 */
	void assertEquals(String msg, Object expected, Object actual) {
		if (expected instanceof String && actual instanceof String) {
			String o1 = ((String) expected).replaceAll("\\s", "");
			String o2 = ((String) actual).replaceAll("\\s", "");
			Assert.assertEquals(msg, o1, o2);
		} else {
			Assert.assertEquals(msg, expected, actual);
		}
	}
	
}
