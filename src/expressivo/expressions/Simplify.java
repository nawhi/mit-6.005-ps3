package expressivo.expressions;

import java.util.Map;

class Simplify {
    private Expression expression;

    Simplify(Expression expression) {
        this.expression = expression;
    }

    String withEnvironment(Map<String, Double> rawEnvironment) {
        Map<Variable, Numeric> parsedEnvironment = new EnvironmentParser(rawEnvironment).asExpressionMap();

        for (Map.Entry entry: parsedEnvironment.entrySet()) {
           expression = expression.replace((Variable) entry.getKey(), (Numeric) entry.getValue());
        }
        return expression.reduced().toString();

    }

}
