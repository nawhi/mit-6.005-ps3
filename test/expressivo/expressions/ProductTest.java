package expressivo.expressions;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static expressivo.expressions.Numeric.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ProductTest {
    @Test
    public void identicalProductsCompareEqual() {
        assertThat(aProduct()).isEqualTo(aProduct());
    }

    private Expression aProduct() {
        return new Product(new Numeric("1"), new Variable("foo"));
    }

    @Test public void productsCommuteWithoutArbitraryParens() {
        Product p1 = new Product(new Numeric("1"), new Numeric("2"));
        Product p2 = new Product(new Numeric("3"), new Numeric("4"));
        assertThat(new Product(p1, p2).toString()).isEqualTo("1*2*3*4");
    }

    @Test
    public void productsWithSwappedValuesAreNotEqual() {
        Variable a = new Variable("a");
        Variable b = new Variable("b");
        assertThat(new Product(a, b)).isNotEqualTo(new Product(b, a));
    }

    @Test
    @Parameters(source = DifferentiateTestData.class)
    public void productsCanBeDifferentiated(Expression input, Variable variable, Expression expectedResult) {
        assertThat(input.differentiate(variable)).isEqualTo(expectedResult);
    }

    public static class DifferentiateTestData {
        public static Object[] provideData() {
            Variable x = new Variable("x");
            Expression c = new Numeric("255");
            return new Object[] {
                new Object[] { new Product(c, c), x, new Sum(new Product(c, ZERO), new Product(c, ZERO)) },
                new Object[] { new Product(x, c), x, new Sum(new Product(x, ZERO), new Product(c, ONE)) },
                new Object[] { new Product(x, x), x, new Sum(new Product(x, ONE), new Product(x, ONE)) }
            };
        }
    }

    @Test
    @Parameters(source = SimplifyTestData.class)
    public void productsCanBeSimplified(Product input, Expression expectedResult) {
        assertThat(input.simplified()).isEqualTo(expectedResult);
    }

    public static class SimplifyTestData {
        public static Object[] provideData() {
            Variable x = new Variable("x");
            Expression c = new Numeric("255");
            return new Object[] {
                    new Object[] { new Product(TWO, TWO), new Numeric("4") },
                    new Object[] { new Product(x, ZERO), ZERO },
                    new Object[] { new Product(x, ONE), x },
                    new Object[] { new Product(ONE, new Product(x, ONE)), x },
                    new Object[] { new Product(x, x), new Product(x, x) },
                    new Object[] { new Product(ZERO, new Product(ZERO, ZERO)), ZERO }
            };
        }
    }
}
