package expressivo.expressions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ParenthesisedExpressionTest {

    @Test
    public void ExpressionAddsParenthesesWhenNeeded() {
        Variable a = new Variable("a");
        Expression multFirst = new Sum(new Product(a,a), new Numeric("1"));
        Expression addFirst = new Product(new Sum(a, new Numeric("1")), a);

        assertEquals("Shouldn't add parens unless necessary", "a*a+1", multFirst.toString());
        assertEquals("Should add parens to keep BIDMAS order", "(a+1)*a", addFirst.toString());
        assertNotEquals("Non-commutative expressions in different order shouldn't be equal", multFirst, addFirst);

        Variable b = new Variable("b");
        Expression aabb = new Sum(new Sum(a,a), new Sum(b,b));
        assertEquals("Should keep BIDMAS order (longer sum)",
                "(a+a+b+b)*2", new Product(aabb, new Numeric("2")).toString());

        // These also check for any aliasing bugs by passing in the same object twice
        assertEquals("Shouldn't add parens unless necessary in complex subexpressions",
                "(a+1)*a*(a+1)*a", new Product(addFirst, addFirst).toString());
        assertEquals("Should add parens to keep BIDMAS in complex subexpressions",
                "(a*a+1)*(a*a+1)", new Product(multFirst, multFirst).toString());

    }
}
