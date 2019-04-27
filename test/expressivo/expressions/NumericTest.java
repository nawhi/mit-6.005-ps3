package expressivo.expressions;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static expressivo.expressions.Numeric.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnitParamsRunner.class)
public class NumericTest {

    @Test
    @Parameters({
            "1|1",
            "1.05|1.05",
            "1.050|1.05",
            "1.00|1",
            "1.00000000000000000000001|1.00000000000000000000001"
    })
    public void identicalNumericsRepresentedDifferentlyShouldBeEqual(String value1, String value2) {
        BigDecimal v1 = new BigDecimal(value1);
        BigDecimal v2 = new BigDecimal(value2);
        assertThat(new Numeric(v1)).isEqualTo(new Numeric(v2));
        assertThat(new Numeric(v1).toString()).isEqualTo(new Numeric(v2).toString());
    }

    @Test
    public void numbersGreaterThanIntSizeAreNotClipped() {
        assertThat(new Numeric(somethingBiggerThanIntMax()).toString())
                .isEqualTo(somethingBiggerThanIntMax().toString());
    }

    private BigDecimal somethingBiggerThanIntMax() {
        return new BigDecimal(Integer.MAX_VALUE).add(new BigDecimal(1));
    }

    @Test
    @Parameters({
            "1",
            "1.234",
            "5000"
    })
    public void numericsAlwaysDifferentiateToZero(String value) {
        Variable x = new Variable("x");
        Numeric numeric = new Numeric(new BigDecimal(value));
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
        assertThat(TWO.multiplyBy(TWO)).isEqualTo(new Numeric(4));
    }
}
