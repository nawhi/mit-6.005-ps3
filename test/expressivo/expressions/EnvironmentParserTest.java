package expressivo.expressions;

import org.junit.Test;

import java.util.Map;

import static expressivo.expressions.Numeric.ONE;
import static expressivo.expressions.Numeric.TWO;
import static org.assertj.core.api.Assertions.assertThat;

public class EnvironmentParserTest {

    @Test
    public void convertsMapOfPrimitivesToMapOfExpressions() {
        assertThat(new EnvironmentParser(Map.of("x", 1., "y", 2.)).asExpressionMap())
                .isEqualTo(Map.of(
                        new Variable("x"), ONE,
                        new Variable("y"), TWO));
    }
}