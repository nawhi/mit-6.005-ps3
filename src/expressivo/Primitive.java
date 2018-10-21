package expressivo;

public abstract class Primitive implements Expression {
	
	@Override public boolean precedes(Expression other) {
		return true; // Primitives never need brackets
	}
	
	@Override public Expression simplify() {
		return this;
	}

}
