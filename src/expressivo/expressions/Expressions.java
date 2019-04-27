package expressivo.expressions;

import java.util.Map;

public class Expressions {
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        return new ParseExpression().parse(input);
    }

    public static String differentiate(String rawExpression, String variable) {
        return new Differentiate(parse(rawExpression)).withRespectTo(variable);
    }

    public static String simplify(String rawExpression, Map<String, Double> environment) {
        return new Simplify(parse(rawExpression)).withEnvironment(environment);
    }
}
