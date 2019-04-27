package expressivo.expressions;

import org.junit.Test;

import static expressivo.expressions.Numeric.ONE;
import static expressivo.expressions.Numeric.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

public class VariableTest {

    private final Variable x = new Variable("x");
    private final Variable y = new Variable("y");

    @Test
    public void variableDifferentiatedByItselfIsOne() {
        assertThat(x.differentiate(x)).isEqualTo(ONE);
    }

    @Test
    public void variableDifferentiatedByDifferentVariableIsZero() {
        assertThat(y.differentiate(x)).isEqualTo(ZERO);
    }

    @Test
    public void variableIgnoresReplaceCallWithDifferentVariable() {
        assertThat(x.replace(y, ONE)).isEqualTo(x);
    }

    @Test
    public void variableReplacesItselfWithNumeric() {
        assertThat(x.replace(x, ONE)).isEqualTo(ONE);
    }

    @Test
    public void variableReducedReturnsItself() {
        assertThat(x.reduced()).isEqualTo(x);
    }
}
