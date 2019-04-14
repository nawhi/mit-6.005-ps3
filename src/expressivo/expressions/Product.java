package expressivo.expressions;

import static expressivo.expressions.Numeric.ONE;
import static expressivo.expressions.Numeric.ZERO;

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
		if (lvalue instanceof Numeric && rvalue instanceof Numeric) {
			return ((Numeric)lvalue).times((Numeric)rvalue);
		}
		if (lvalue.equals(ZERO) || rvalue.equals(ZERO)) {
			return ZERO;
		}
		if (lvalue.equals(ONE)) {
			return rvalue;
		}
		if (rvalue.equals(ONE)) {
			return lvalue;
		}
		return new Product(lvalue.simplified(), rvalue.simplified());
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Product)
			return this.lvalue.equals(((Product) other).lvalue)
				   && this.rvalue.equals(((Product) other).rvalue);
		return super.equals(other);
	}
}
