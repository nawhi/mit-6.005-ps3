package expressivo.expressions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
