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
		Sum simplifiedThis = new Sum(lvalue.simplified(), rvalue.simplified());
		if (simplifiedThis.lvalue instanceof Numeric && simplifiedThis.rvalue instanceof Numeric) {
			return ((Numeric) simplifiedThis.lvalue).plus((Numeric) simplifiedThis.rvalue);
		} else if (simplifiedThis.lvalue instanceof Variable && simplifiedThis.lvalue.equals(simplifiedThis.rvalue)) {
			return new Product(TWO, simplifiedThis.lvalue);
		} else if (simplifiedThis.lvalue.equals(ZERO)) {
			return simplifiedThis.rvalue;
		} else if (simplifiedThis.rvalue.equals(ZERO))
			return simplifiedThis.lvalue;
		return new Sum(simplifiedThis.lvalue.simplified(), simplifiedThis.rvalue.simplified());
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Sum)
			return this.lvalue.equals(((Sum) other).lvalue)
				   && this.rvalue.equals(((Sum) other).rvalue);
		return super.equals(other);
	}
}
