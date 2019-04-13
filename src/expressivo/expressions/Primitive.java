package expressivo.expressions;

abstract class Primitive implements Expression {
	
	@Override public boolean precedes(Expression other) {
		return true; // Primitives never need brackets
	}

}
