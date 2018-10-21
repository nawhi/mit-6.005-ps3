package expressivo;

public class Product extends BinOp {
	
	public Product(Expression lvalue, Expression rvalue) {
		super(lvalue, rvalue);
	}
	
	@Override 
	protected String operator() {
		return "*";
	}
	
	@Override 
	public boolean precedes(Expression other) {
		return true;
	}
	
	@Override
	public Expression simplify() {
		throw new RuntimeException("not implemented");
	}

	@Override 
	public boolean equals(Object other) {
		if (other instanceof Product)
			return this.lvalue.equals(((Product) other).lvalue)
				   && this.rvalue.equals(((Product) other).rvalue);
		return super.equals(other);
	}
	

}
