package expressivo.expressions;

class Differentiate {
    private Expression expression;

    Differentiate(Expression expression) {
        this.expression = expression;
    }

    String withRespectTo(String rawVariable) {
        Variable variable = parseVariable(rawVariable);
        return expression.differentiate(variable).toString();
    }

    private static Variable parseVariable(String rawVariable) {
        Expression ex = Expression.parse(rawVariable);
        if (!(ex instanceof Variable))
            throw new IllegalArgumentException(
                    "Can't differentiate with respect to '" + ex
                    + "' because it's not a variable");
        return (Variable) ex;
    }
}
