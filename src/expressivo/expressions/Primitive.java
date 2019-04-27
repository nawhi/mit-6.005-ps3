package expressivo.expressions;

abstract class Primitive implements Expression {
	
	@Override
	public boolean precedes(Expression other) {
		return true;
	}

	@Override
	public Expression reduced() {
		return this;
	}
}
