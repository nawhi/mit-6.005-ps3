package expressivo.expressions;

import org.junit.Test;

import static expressivo.expressions.Numeric.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

public class VariableTest {

    private final Variable x = new Variable("x");
    private final Variable y = new Variable("y");

    @Test
    public void variableDifferentiatedByItselfIsOne() {
        assertThat(x.differentiate(x)).isEqualTo(new Numeric("1"));
    }

    @Test
    public void variableDifferentiatedByDifferentVariableIsZero() {
        assertThat(y.differentiate(x)).isEqualTo(ZERO);
    }
}
