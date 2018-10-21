package expressivo;

public class Sum extends BinOp {

	public Sum(Expression lvalue, Expression rvalue) {
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
	public Expression simplify() {
		if (lvalue instanceof Numeric && rvalue instanceof Numeric)
			return new Numeric(((Numeric) lvalue).getValue() + ((Numeric) rvalue).getValue());

		if (lvalue instanceof Variable && lvalue.equals(rvalue))
			return new Sum(new Numeric(2), lvalue);
		
		return new Sum(lvalue.simplify(), rvalue.simplify());
	}
	
	
	@Override 
	public boolean equals(Object other) {
		if (other instanceof Sum)
			return this.lvalue.equals(((Sum) other).lvalue)
				   && this.rvalue.equals(((Sum) other).rvalue);
		return super.equals(other);
	}
}
