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
	public Expression replace(Variable variable, Numeric value) {
		return new Product(lvalue.replace(variable, value), rvalue.replace(variable, value));
	}

	@Override 
	public boolean precedes(Expression other) {
		return true;
	}

//	@Override
//	public Expression simplified() {
//		Product simplified = new Product(lvalue.simplified(), rvalue.simplified());
//		if (simplified.lvalue instanceof Numeric && simplified.rvalue instanceof Numeric) {
//			return ((Numeric)simplified.lvalue).times((Numeric)simplified.rvalue);
//		}
//		if (simplified.lvalue.equals(ZERO) || simplified.rvalue.equals(ZERO)) {
//			return ZERO;
//		}
//		if (simplified.lvalue.equals(ONE)) {
//			return simplified.rvalue;
//		}
//		if (simplified.rvalue.equals(ONE)) {
//			return simplified.lvalue;
//		}
//		return simplified;
//	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Product)
			return this.lvalue.equals(((Product) other).lvalue)
				   && this.rvalue.equals(((Product) other).rvalue);
		return super.equals(other);
	}
}
