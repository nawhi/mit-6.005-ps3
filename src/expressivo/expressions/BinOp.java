package expressivo.expressions;

import java.util.Objects;

abstract class BinOp extends BaseExpression {
	
	final Expression lvalue;
	final Expression rvalue;
	
	BinOp(Expression lvalue, Expression rvalue) {
		this.lvalue = lvalue;
		this.rvalue = rvalue;
	}

	protected abstract String operator();

	@Override
	public abstract Expression differentiate(Variable variable);
	
	@Override
	public int hashCode() {
		return Objects.hash(lvalue, rvalue);
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
