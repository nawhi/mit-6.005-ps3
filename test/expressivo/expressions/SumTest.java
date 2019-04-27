package expressivo.expressions;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static expressivo.expressions.Numeric.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SumTest {

    @Test
    public void identicalSumsShouldBeEqual() {
        Sum aSum = new Sum(ONE, new Variable("foo"));
        assertThat(aSum).isEqualTo(aSum);
    }

    @Test public void sumsCommuteWithoutArbitraryParens() {
        Sum s1 = new Sum(ONE, TWO);
        Sum s2 = new Sum(new Numeric(3), new Numeric(4));
        assertThat(new Sum(s1, s2).toString()).isEqualTo("1+2+3+4");
    }

    @Test
    public void sumsWithSwappedValuesAreNotEqual() {
        Variable a = new Variable("a");
        Variable b = new Variable("b");
        assertThat(new Sum(a, b)).isNotEqualTo(new Sum(b, a));
    }

    @Test
    public void differentiatedSumIsSumOfDifferentiatedComponents() {
        Variable x = new Variable("x");
        assertThat(new Sum(x, x).differentiate(x)).isEqualTo(new Sum(ONE, ONE));
    }

    @Test
    public void sumsDoNotReduceIfEitherValueIsAVariable() {
        Variable x = new Variable("x");
        Sum sum = new Sum(ONE, x);
        assertThat(sum.reduced()).isEqualTo(sum);
    }

    @Test
    public void sumsReduceByAddingComponentNumerics() {
        assertThat(new Sum(ONE, ONE).reduced()).isEqualTo(TWO);
    }

    @Test
    public void nestedSumsReduceByAddingAllComponents() {
        assertThat(new Sum(ONE, new Sum(ONE, new Sum(ONE, ONE))).reduced()).isEqualTo(new Numeric(4));
    }
}
