package expressivo.expressions;

import org.junit.Test;

import static expressivo.expressions.Numeric.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

public class VariableTest {

    @Test
    public void variableDifferentiatedByDifferentVariableIsZero() {
        assertThat(new Variable("y").differentiate(new Variable("x"))).isEqualTo(ZERO);
    }

    @Test
    public void variableDifferentiatedByItselfIsItself() {
        assertThat(new Variable("x").differentiate(new Variable("x"))).isEqualTo(new Variable("x"));
    }
}
