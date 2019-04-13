package expressivo.commands;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class DifferentiateTest {

    @Test
    @Parameters({
            "1|x|0",
            "foo|x|0",
            "x|x|1",
            "1+1|x|0",

    })
    public void simpleExpressionsCanBeDifferentiated(String inputExpr, String variable, String expectedOutput) {
        assertThat(Commands.differentiate(inputExpr, variable)).isEqualTo(expectedOutput);
    }
}
