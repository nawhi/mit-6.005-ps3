package expressivo.parser;

import expressivo.expressions.Expressions;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ComposedExpressionParseTest {

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
        assertThat(Expressions.parse(input).toString()).isEqualTo(input);
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
        assertThat(Expressions.parse(input).toString()).isEqualTo(input);
    }
}
