package expressivo.expressions;

import org.junit.Test;

import static expressivo.expressions.Numeric.ONE;
import static expressivo.expressions.Numeric.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class ParenthesisedExpressionTest {

    private final Variable a = new Variable("a");
    private final Product aSquared = new Product(a, a);
    private final Numeric one = ONE;
    private final Sum aPlusOne = new Sum(a, one);

    @Test
    public void parensAreNotAddedWhenUnnecessary() {
        assertThat(new Sum(aSquared, one).toString()).isEqualTo("a*a+1");
    }

    @Test
    public void parensAreAddedToPreserveOperatorOrder() {
        assertThat(new Product(aPlusOne, a).toString()).isEqualTo("(a+1)*a");
    }

    @Test
    public void expressionAddsParenthesesWhenNeeded() {
        Variable a = new Variable("a");
        Expression multFirst = new Sum(new Product(a,a), ONE);
        Expression addFirst = new Product(new Sum(a, ONE), a);


        Variable b = new Variable("b");
        Expression aabb = new Sum(new Sum(a,a), new Sum(b,b));
        assertEquals("Should keep BIDMAS order (longer sum)",
                "(a+a+b+b)*2", new Product(aabb, TWO).toString());

        // These also check for any aliasing bugs by passing in the same object twice
        assertEquals("Shouldn't add parens unless necessary in complex subexpressions",
                "(a+1)*a*(a+1)*a", new Product(addFirst, addFirst).toString());
        assertEquals("Should add parens to keep BIDMAS in complex subexpressions",
                "(a*a+1)*(a*a+1)", new Product(multFirst, multFirst).toString());

    }
}
