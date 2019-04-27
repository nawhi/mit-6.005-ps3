package expressivo;

import expressivo.expressions.ProductTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class CommandsTest {

    @Test
    @Parameters({
            "1|x|0",
            "c|x|0",
            "x|x|1",


            // these assume simplification
//            "1+1|x|0",
//            "x+2|x|1",
//            "x+x|x|2",
//
//            "1*x|x|1",
//            "x*x|x|2*x",
//            "x*x*x|x|3*x*x" // fails; gives x*2*x + x*x
    })
    public void expressionsCanBeDifferentiated(String inputExpr, String variable, String expectedOutput) {
        assertThat(Commands.differentiate(inputExpr, variable)).isEqualTo(expectedOutput);
    }

//    @Ignore("acceptance")
    @Test
    @Parameters(source = SimplifyTestData.class)
    public void expressionsCanBeSimplified2(String input, Map<String, Double> environment, String expectedResult) {
        assertThat(Commands.simplify(input, environment)).isEqualTo(expectedResult);
    }

    public static class SimplifyTestData {
        public static Object[] provideData() {
            return new Object[] {
                new Object[] { "x", Map.of("x", 5.), "5" }
            };
        }
    }
}
