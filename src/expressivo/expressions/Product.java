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
		return new Product(lvalue.differentiate(variable), rvalue.differentiate(variable));
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
}
