package expressivo;

public class Product extends BinOp {
	
	public Product(Expression lvalue, Expression rvalue) {
		super(lvalue, rvalue);
	}
	
	@Override public boolean precedes(Expression other) {
		return true;
	}
	
	@Override public String toString() {
		// Operator precedence: 
		// if lvalue is a Sum, it needs brackets
		if (!lvalue.precedes(rvalue))
			return String.format("(%s*%s)", lvalue.toString(), rvalue.toString());
		return String.format("%s*%s", lvalue.toString(), rvalue.toString());
	}
	
	@Override public int hashCode() {
		throw new RuntimeException("not implemented");
	}

	@Override public boolean equals(Object other) {
		if (other instanceof Product)
			return this.lvalue.equals(((Product) other).lvalue)
				   && this.rvalue.equals(((Product) other).rvalue);
		return super.equals(other);
	}
	

}
