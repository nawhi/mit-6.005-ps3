package expressivo.expressions;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static expressivo.expressions.Numeric.ONE;
import static expressivo.expressions.Numeric.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnitParamsRunner.class)
public class NumericTest {

    private static final Numeric ZERO = new Numeric("0");

    @Test
    @Parameters({
            "1|1",
            "1.05|1.05",
            "1.050|1.05",
            "1.00|1",
            "1.00000000000000000000001|1.00000000000000000000001"
    })
    public void identicalNumericsRepresentedDifferentlyShouldBeEqual(String value1, String value2) {
        assertThat(new Numeric(value1)).isEqualTo(new Numeric(value2));
        assertThat(new Numeric(value1).toString()).isEqualTo(new Numeric(value2).toString());
    }

    @Test
    public void numericThrowsIfPassedInvalidInitialValue() {
        assertThatThrownBy(() -> new Numeric("foo")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void numbersGreaterThanIntSizeAreNotClipped() {
        String bigValue = somethingBiggerThanIntMax();
        assertThat(new Numeric(bigValue).toString()).isEqualTo(bigValue);
    }

    private String somethingBiggerThanIntMax() {
        return new BigDecimal(Integer.MAX_VALUE).add(new BigDecimal(1)).toString();
    }

    @Test
    @Parameters({
            "1",
            "1.234",
            "5000"
    })
    public void numericsAlwaysDifferentiateToZero(String value) {
        Variable x = new Variable("x");
        Numeric numeric = new Numeric(value);
        assertThat(numeric.differentiate(x)).isEqualTo(ZERO);
    }

    @Test
    public void numericsReduceToThemselves() {
        assertThat(ONE.reduced()).isEqualTo(ONE);
    }

    @Test
    public void numericsAddToEachOther() {
        assertThat(ONE.addTo(ONE)).isEqualTo(TWO);
    }

    @Test
    public void numericsMultiplyByEachOther() {
        assertThat(TWO.multiplyBy(TWO)).isEqualTo(new Numeric("4"));
    }
}
