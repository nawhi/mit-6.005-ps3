/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandsDifferentiateTest {

	@Test
	public void differentiatingNumericShouldReturnZero() {
		assertEquals("0", Commands.differentiate("5.0", "x"));
	}
    
}
