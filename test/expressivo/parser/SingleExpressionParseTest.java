package expressivo.parser;

import expressivo.expressions.Expression;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

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
			"a+b",
			"x+3",
			"3+x",
			"0.9+3.356",
			"x+y+z",
			"1.2+3.5+f+98+2345+0.3"
	})
	public void sumsCanBeParsed(String input) {
		assertThat(Expression.parse(input).toString()).isEqualTo(input);
	}

	@Test
	@Parameters({
			"a*b",
			"x*3",
			"3*x",
			"0.9*3.356",
			"x*y*z",
			"1.2*3.5*f*98*2345*0.3"
	})
	public void productsCanBeParsed(String input) {
		assertThat(Expression.parse(input).toString()).isEqualTo(input);
	}
}
