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
		return !(other instanceof Product); 
	}

	@Override
	public Expression differentiate(Variable variable) {
		return new Sum(lvalue.differentiate(variable), rvalue.differentiate(variable));
	}

//	@Override
//	public Expression simplified() {
//		Sum simplified = new Sum(lvalue.simplified(), rvalue.simplified());
//		if (simplified.lvalue instanceof Numeric && simplified.rvalue instanceof Numeric) {
//			return ((Numeric) simplified.lvalue).plus((Numeric) simplified.rvalue);
//		}
//		if (simplified.lvalue.equals(simplified.rvalue)) {
//			return new Product(TWO, simplified.lvalue);
//		}
//		if (simplified.lvalue.equals(ZERO)) {
//			return simplified.rvalue;
//		}
//		if (simplified.rvalue.equals(ZERO))
//			return simplified.lvalue;
//
//		return simplified;
//	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Sum)
			return this.lvalue.equals(((Sum) other).lvalue)
				   && this.rvalue.equals(((Sum) other).rvalue);
		return super.equals(other);
	}
}
