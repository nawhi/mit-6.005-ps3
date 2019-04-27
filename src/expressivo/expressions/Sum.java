package expressivo.expressions;

class Sum extends BinOp {

    Sum(Expression lvalue, Expression rvalue) {
        super(lvalue, rvalue);
    }

    @Override
    protected String operator() {
        return "+";
    }

    @Override
    public boolean precedes(Expression other) {
        return !(other instanceof Product);
    }

    @Override
    public Expression differentiate(Variable variable) {
        return new Sum(lvalue.differentiate(variable), rvalue.differentiate(variable));
    }

    @Override
    public Expression replace(Variable variable, Numeric value) {
        return new Sum(lvalue.replace(variable, value), rvalue.replace(variable, value));
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Sum)
            return this.lvalue.equals(((Sum) other).lvalue)
                    && this.rvalue.equals(((Sum) other).rvalue);
        return super.equals(other);
    }

    @Override
    public Expression reduced() {
        Sum reduced = new Sum(lvalue.reduced(), rvalue.reduced());
        return reduced.lvalue.addTo(reduced.rvalue);
    }
}
