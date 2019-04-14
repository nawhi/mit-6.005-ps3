package expressivo.expressions;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static expressivo.expressions.Numeric.ONE;
import static expressivo.expressions.Numeric.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SumTest {

    @Test
    public void identicalSumsShouldBeEqual() {
        Sum aSum = new Sum(new Numeric("1"), new Variable("foo"));
        assertThat(aSum).isEqualTo(aSum);
    }

    @Test public void sumsCommuteWithoutArbitraryParens() {
        Sum s1 = new Sum(new Numeric("1"), new Numeric("2"));
        Sum s2 = new Sum(new Numeric("3"), new Numeric("4"));
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
    @Parameters(source = SimplifyTestData.class)
    public void sumsCanBeSimplified(Sum input, Expression expectedResult) {
        assertThat(input.simplified()).isEqualTo(expectedResult);
    }

    public static class SimplifyTestData {
        public static Object[] provideSums() {
            Variable x = new Variable("x");
            Variable y = new Variable("y");
            Numeric two = new Numeric("2");
            return new Object[] {
                    new Object[] { new Sum(x, y), new Sum(x, y) },
                    new Object[] { new Sum(x, ONE), new Sum(x, ONE) },
                    new Object[] { new Sum(ONE, ONE), two },
                    new Object[] { new Sum(x, x), new Product(two, x) },
                    new Object[] { new Sum(ONE, ZERO), ONE },
                    new Object[] { new Sum(x, ZERO), x },
                    new Object[] { new Sum(ZERO, new Sum(ZERO, ZERO)), ZERO },
            };
        }
    }
}
