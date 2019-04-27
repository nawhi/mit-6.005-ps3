package expressivo.parser;

import expressivo.expressions.Expressions;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ParenthesisedExpressionParseTest {

	@Test
	@Parameters({
			"x+y*z",
			"x*y+z",
			"a+b*c+d",
			"a*b+c*d",
			"a*b*c+d*e*f",
			"a+b+c*d+e+f"
	})
	public void parenthesesAreNotAdded(String input) {
		assertThat(Expressions.parse(input).toString()).isEqualTo(input);
	}

	@Test
	@Parameters({
			"(a)|a",
			"((a))|a",
			"(1)|1",
			"(a)+(b)|a+b",
			"(a+b)|a+b",
			"(a)*(b)|a*b",
			"(a*b)|a*b",
			"(2*a)+1|2*a+1"
	})
	public void spuriousParenthesesAreStripped(String input, String expectedOutput) {
		assertThat(Expressions.parse(input).toString()).isEqualTo(expectedOutput);
	}
	
	@Test
	@Parameters({
			"2*(a+1)",
			"(b+c)*(d+e)"
	})
	public void necessaryParenthesesArePreserved(String input) {
		assertThat(Expressions.parse(input).toString()).isEqualTo(input);
	}
}
