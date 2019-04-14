package expressivo.expressions;

public class Differentiate {
    private String rawExpression;

    public Differentiate(String rawExpression) {
        this.rawExpression = rawExpression;
    }

    public static Variable parseVariable(String rawVariable) {
        Expression ex = Expression.parse(rawVariable);
        if (!(ex instanceof Variable))
            throw new IllegalArgumentException("Can't differentiate with respect to '" + ex + "' because it's not a variable");
        return (Variable) ex;
    }

    public String withRespectTo(String rawVariable) {
        Expression expression = Expression.parse(rawExpression);
        Variable variable = parseVariable(rawVariable);
        return expression.differentiate(variable).simplified().toString();
    }
}
