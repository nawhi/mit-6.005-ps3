package expressivo.expressions;

public abstract class BaseExpression implements Expression {
    @Override
    public Expression addTo(Expression rvalue) {
        return new Sum(this, rvalue);
    }

    @Override
    public Expression multiplyBy(Expression rvalue) {
        return new Product(this, rvalue);
    }
}
