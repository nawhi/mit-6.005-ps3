package expressivo.expressions;

class Product extends BinOp {
	
	Product(Expression lvalue, Expression rvalue) {
		super(lvalue, rvalue);
	}
	
	@Override 
	protected String operator() {
		return "*";
	}

	@Override
	public Expression differentiate(Variable variable) {
		// d(u*v)/dx = u(dv/dx) * v(du/dx)
		return new Sum(
				new Product(lvalue, rvalue.differentiate(variable)),
				new Product(rvalue, lvalue.differentiate(variable))
		);
	}

	@Override
	public Expression replace(Variable variable, Numeric value) {
		return new Product(lvalue.replace(variable, value), rvalue.replace(variable, value));
	}

	@Override 
	public boolean precedes(Expression other) {
		return true;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Product)
			return this.lvalue.equals(((Product) other).lvalue)
				   && this.rvalue.equals(((Product) other).rvalue);
		return super.equals(other);
	}

	@Override
	public Expression reduced() {
		Product reduced = new Product(lvalue.reduced(), rvalue.reduced());
		return reduced.lvalue.multiplyBy(reduced.rvalue);
	}
}
