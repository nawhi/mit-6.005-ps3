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
    public void productsDoNotReduceIfEitherValueIsAVariable() {
        Variable x = new Variable("x");
        Product product = new Product(ONE, x); // todo: should reduce in future
        assertThat(product.reduced()).isEqualTo(product);
    }

    @Test
    public void productsToNotReduceIfOneValueIsAVariable() {
        Product p = new Product(new Numeric("10.5"), new Variable("z"));
        assertThat(p.reduced()).isEqualTo(p);
    }

    @Test
    public void productsReduceByMultiplyingComponentNumerics() {
        assertThat(new Product(TWO, TWO).reduced()).isEqualTo(new Numeric("4"));
    }

    @Test
    public void nestedProductsReduceByMultiplyingAllComponents() {
        assertThat(new Product(TWO, new Product(TWO, new Product(TWO, TWO))).reduced()).isEqualTo(new Numeric("16"));
    }
}
