package expressivo.parser;

import expressivo.expressions.Expression;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ParenthesisedExpressionParseTest {

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
		assertThat(Expression.parse(input).toString()).isEqualTo(expectedOutput);
	}
	
	@Test
	@Parameters({
			"2*(a+1)",
			"(b+c)*(d+e)"
	})
	public void necessaryParenthesesArePreserved(String input) {
		assertThat(Expression.parse(input).toString()).isEqualTo(input);
	}
}
