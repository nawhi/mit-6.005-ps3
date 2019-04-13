package expressivo.parser;

import expressivo.expressions.Expression;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class PrimitiveParseTest {

	@Test
	@Parameters({
			"0",
			"1",
			"10",

			"1.5",
			"3.1415926535",
	})
	public void numbersCanBeParsed(String input) {
		assertThat(Expression.parse(input).toString()).isEqualTo(input);
	}

	@Test
	@Parameters({
			"01|1",
			"0001|1",
			".5|0.5",
			"2.0|2",
			"2.300|2.3",
			"0025.300|25.3"
	})
	public void zeroesAreStandardisedWhenParsingNumbers(String input, String expectedOutput) {
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
}
