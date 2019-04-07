package expressivo.expressions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumTest {

    @Test
    public void identicalSumsShouldBeEqual() {
        assertThat(aSum()).isEqualTo(aSum());
    }

    private Expression aSum() {
        return new Sum(new Numeric("1"), new Variable("foo"));
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

}
