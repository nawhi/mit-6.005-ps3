package expressivo.expressions;

import static expressivo.expressions.Numeric.TWO;
import static expressivo.expressions.Numeric.ZERO;

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
	public Expression simplified() {
		if (lvalue instanceof Numeric && rvalue instanceof Numeric) {
			return ((Numeric) lvalue).plus((Numeric) rvalue);
		} else if (lvalue instanceof Variable && lvalue.equals(rvalue)) {
			return new Product(TWO, lvalue);
		} else if (lvalue.equals(ZERO)) {
			return rvalue;
		} else if (rvalue.equals(ZERO))
			return lvalue;
		return new Sum(lvalue.simplified(), rvalue.simplified());
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Sum)
			return this.lvalue.equals(((Sum) other).lvalue)
				   && this.rvalue.equals(((Sum) other).rvalue);
		return super.equals(other);
	}
}
