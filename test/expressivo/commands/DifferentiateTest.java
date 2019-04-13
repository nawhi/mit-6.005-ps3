package expressivo.commands;

import expressivo.commands.Commands;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DifferentiateTest {

    @Ignore
    @Test
    public void expressionCanBeDifferentiatedWithRespectToAVariable() {
        String differentiated = Commands.differentiate("4*x*x*x + 3*x*x + 2*pi*x + 3*pi", "x");
        assertThat(differentiated).isEqualToIgnoringWhitespace("12*x*x + 6*x");
    }
}
