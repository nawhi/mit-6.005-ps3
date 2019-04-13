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
		return !(other instanceof Product); // (x+1)*3 != x+1*3
	}
	
	@Override
	public Expression differentiate(Variable variable) {
		return new Sum(lvalue.differentiate(variable), rvalue.differentiate(variable));
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Sum)
			return this.lvalue.equals(((Sum) other).lvalue)
				   && this.rvalue.equals(((Sum) other).rvalue);
		return super.equals(other);
	}
}
