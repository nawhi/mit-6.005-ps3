package expressivo;

public abstract class BinOp implements Expression {
	
	public final Expression lvalue;
	public final Expression rvalue;
	
	public BinOp(Expression lvalue, Expression rvalue) {
		this.lvalue = lvalue;
		this.rvalue = rvalue;
	}
	
	@Override public int hashCode() { 
		return 5 + lvalue.hashCode() ^ rvalue.hashCode() << 1;
	}
}
