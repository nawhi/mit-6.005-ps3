package expressivo.parser;

import expressivo.expressions.Expression;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnitParamsRunner.class)
public class InvalidInputTest {

    @Test
    @Parameters({
            "1.2.3",
            ".2.",
            "f1.3",
            ".",
            "-3.5", // for now
            "2\\,345\\,678", // for now

            "2a",
            "2.5a",

            "2+",
            "+2",

            "2*",
            "*2"
    })
    public void invalidInputsThrowAnException(String input) {
        assertThatThrownBy(() -> Expression.parse(input)).hasMessageStartingWith("Syntax error");
    }
}
