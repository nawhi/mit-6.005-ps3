package expressivo.parser;

import expressivo.expressions.Expression;
import expressivo.expressions.Product;
import expressivo.expressions.Sum;
import expressivo.expressions.Variable;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Fail.fail;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class SingleExpressionParseTest {

	@Test
	@Parameters({
			"0|0",
			"1|1",
			"01|1",
			"10|10"
	})
	public void integersCanBeParsed(String input, String expectedOutput) {
		assertThat(Expression.parse(input).toString()).isEqualTo(expectedOutput);
	}

	@Test
	@Parameters({
			"1.5|1.5",
			".5|0.5",
			"2.0|2", // OK for the time being
			"2.300|2.3",
			"3.1415926535|3.1415926535",
			"0025.300|25.3"
	})
	public void floatsCanBeParsed(String input, String expectedOutput) {
		assertThat(Expression.parse(input).toString()).isEqualTo(expectedOutput);
	}

	@Test
	@Parameters({
			"myvar",
			"_123",
			"_foo",
			"_f1",
			"_1f"
	})
	public void variablesCanBeParsed(String input) {
		assertThat(Expression.parse(input).toString()).isEqualTo(input);
	}

	@Test
	@Parameters({
			"1.2.3",
			".2.",
			"f1.3",
			".",

			"-3.5", // for now
			"2\\,345\\,678" // for now
	})
	public void invalidNumericsThrowAnException(String input) {
		assertThatThrownBy(() -> Expression.parse(input)).hasMessageStartingWith("Syntax error");
	}


	@Test
	public void testSimpleSums() {
		Expression sum = Expression.parse("a+b");
		assertEquals(new Sum(new Variable("a"), new Variable("b")), sum);

		parsesIdentically("x+3");
		parsesIdentically("3+x");
		parsesIdentically("0.9+3.356");
	}

	@Test
	@Parameters({
			"2a",
			"2.5a"
	})
	public void invalidVariablesThrowAnException(String input) {
		assertThatThrownBy(() -> Expression.parse(input)).hasMessageStartingWith("Syntax error");
	}

	@Test
	public void testChainedSums() {
		parsesIdentically("x+y+z");
		assertEquals("1.2+3.5+f+98+2345+0.3",
				Expression.parse("1.2 + 3.5 + f + 98 + 2345 + .3").toString());
	}
	
	@Test
	public void testSimpleProducts() {
		Expression prod = Expression.parse("a*b");
		assertEquals(new Product(new Variable("a"), new Variable("b")), prod);
		
		parsesIdentically("x*3");
		parsesIdentically("3*x");
		parsesIdentically("0.9*3.356");
	}
	
	@Test
	public void testChainedProducts() {
		parsesIdentically("x*y*z");
		assertEquals("1.2*3.5*f*98*2345*0.3",
				Expression.parse("1.2 * 3.5 * f * 98 * 2345 * .3").toString());
	}
	
	private void parseFails(String s) {
		try {
			Expression.parse(s);
			fail("Attempt to parse " + s + " should fail");
		} catch (IllegalArgumentException ex) {
		}
	}
	
	private void parsesIdentically(String s) {
		assertEquals(s, Expression.parse(s).toString());
	}


}
