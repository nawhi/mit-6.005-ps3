package expressivo.expressions;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EnvironmentParserTest {

    @Test
    public void convertsMapOfPrimitivesToMapOfExpressions() {
        assertThat(new EnvironmentParser(Map.of("x", 1., "y", 2.)).asExpressionMap())
                .isEqualTo(Map.of(
                        new Variable("x"), new Numeric("1"),
                        new Variable("y"), new Numeric("2")));
    }
}