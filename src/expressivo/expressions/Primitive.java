package expressivo.expressions;

abstract class Primitive extends BaseExpression {
	
	@Override
	public boolean precedes(Expression other) {
		return true;
	}

	@Override
	public Expression reduced() {
		return this;
	}
}
