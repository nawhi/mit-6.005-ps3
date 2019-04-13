package expressivo.expressions;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

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
        assertThat(new Numeric(value1)).isEqualTo(new Numeric(value2));
        assertThat(new Numeric(value1).toString()).isEqualTo(new Numeric(value2).toString());
    }

    @Test
    public void numericThrowsIfPassedInvalidInitialValue() {
        assertThatThrownBy(() -> new Numeric("foo")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void numbersGreaterThanIntSizeAreNotClipped() {
        String bigValue = new BigDecimal(Integer.MAX_VALUE).add(new BigDecimal(1)).toString();
        assertThat(new Numeric(bigValue).toString()).isEqualTo(bigValue);
    }
}
