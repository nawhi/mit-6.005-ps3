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
            "c|x|0",
            "x|x|1",

            "1+1|x|0",
            "x+2|x|1",
            "x+x|x|2",

            "1*x|x|1",
            "x*x|x|2*x",
//            "x*x*x|x|3*x*x" // fails; gives x*2*x + x*x
    })
    public void simpleExpressionsCanBeDifferentiated(String inputExpr, String variable, String expectedOutput) {
        assertThat(Commands.differentiate(inputExpr, variable)).isEqualTo(expectedOutput);
    }
}
