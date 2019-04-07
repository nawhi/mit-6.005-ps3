package expressivo.expressions;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumericTest {
    @Test
    public void integerNumericHasWholeStringRepresentation() {
        assertThat(new Numeric("1").toString()).isEqualTo("1");
    }

    @Test public void identicalIntegerNumericsShouldBeEqual() {
        assertThat(new Numeric("1")).isEqualTo(new Numeric("1"));
    }

    @Test
    public void identicalFloatNumericsShouldBeEqual() {
        assertThat(new Numeric("1.05")).isEqualTo(new Numeric("1.05"));
    }

    @Test
    public void floatStringShouldNotHaveTrailingZeroes() {
        assertThat(new Numeric("1.050").toString()).isEqualTo("1.05");
    }

    @Test
    public void numericThrowsIfPassedInvalidInitialValue() {
        assertThatThrownBy(() -> new Numeric("foo")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void intShouldEqualWholeNumberFloat() {
        assertThat(new Numeric("1")).isEqualTo(new Numeric("1.00"));
    }

    @Test
    public void smallDecimalPlacesAreNotIgnored() {
        assertThat(new Numeric("1.0")).isNotEqualTo(new Numeric("1.00000000000000000000001"));
    }

    @Test
    public void numbersGreaterThanIntSizeAreNotClipped() {
        String bigValue = new BigDecimal(Integer.MAX_VALUE).add(new BigDecimal(1)).toString();
        assertThat(new Numeric(bigValue).toString()).isEqualTo(bigValue);
    }
}
