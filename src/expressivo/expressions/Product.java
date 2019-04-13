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
	public boolean precedes(Expression other) {
		return true;
	}

	@Override
	public Expression simplified() {
		return null;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Product)
			return this.lvalue.equals(((Product) other).lvalue)
				   && this.rvalue.equals(((Product) other).rvalue);
		return super.equals(other);
	}
}
