package expressivo;

public class Sum extends BinOp {

	public Sum(Expression lvalue, Expression rvalue) {
		super(lvalue, rvalue);
	}
	
	@Override public boolean precedes(Expression other) {
		return !(other instanceof Product); // (x+1)*3 != x+1*3
	}
	
	@Override public String toString() {
		if (!lvalue.precedes(rvalue))
			return String.format("(%s + %s)", lvalue.toString(), rvalue.toString());
		return String.format("%s + %s", lvalue.toString(), rvalue.toString());
	}
	
	@Override public boolean equals(Object other) {
		if (other instanceof Sum)
			return this.lvalue.equals(((Sum) other).lvalue)
				   && this.rvalue.equals(((Sum) other).rvalue);
		return super.equals(other);
	}
}
