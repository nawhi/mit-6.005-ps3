package expressivo.expressions;

abstract class BinOp implements Expression {
	
	final Expression lvalue;
	final Expression rvalue;
	
	BinOp(Expression lvalue, Expression rvalue) {
		this.lvalue = lvalue;
		this.rvalue = rvalue;
	}
	
	/**
	 * Get the string version of the operator for this binary
	 * operation.
	 * @return string of the operator symbol, e.g. * or +
	 */
	protected abstract String operator();
	
	@Override
	public Expression differentiate(Variable variable) {
		throw new RuntimeException("not implemented");
	}
	
	@Override
	public int hashCode() {
		return 5 + lvalue.hashCode() ^ rvalue.hashCode() << 1;
	}
	
	@Override
	public String toString() {
		// Sum.precedes(Product) == false so Product(Sum, Sum) needs brackets
		// Product.precedes(Sum) == true so Sum(Product, Product) doesn't need brackets
		String lstr = lvalue.toString();
		String rstr = rvalue.toString();
		String op = operator();
		return (lvalue.precedes(this) ? lstr : bracket(lstr))
			   + op + (rvalue.precedes(this) ? rstr : bracket(rstr));
	}
	
	private String bracket(String s) {
		return "(" + s + ")";
	}
}
