package expressivo.expressions;

import java.math.BigDecimal;

class Numeric extends Primitive {
	static final Numeric TWO = new Numeric(new BigDecimal(2));
	static final Numeric ZERO = new Numeric(new BigDecimal(0));
	static final Numeric ONE = new Numeric(new BigDecimal(1));

	private final BigDecimal value;
	
	// Representation invariant:
	//   true
	// Safety from rep exposure:
	//   private fields are final

	Numeric(String val) {
		this.value = new BigDecimal(val);
	}

	private Numeric(BigDecimal val) {
		this.value = val;
	}

	@Override
	public Expression differentiate(Variable variable) {
		return ZERO;
	}

	@Override
	public Expression replace(Variable variable, Numeric value) {
		return this;
	}

	@Override
	public String toString() {
		return value.stripTrailingZeros().toPlainString();
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Numeric) {
			return this.value.compareTo(((Numeric)other).value) == 0;
		}
		return super.equals(other);
	}

	@Override
	public Expression addTo(Expression other) {
		if (other instanceof Numeric)
			return new Numeric(((Numeric) other).value.add(value));
		return super.addTo(other);
	}

	@Override
	public Expression multiplyBy(Expression other) {
		if (other instanceof Numeric)
			return new Numeric(((Numeric) other).value.multiply(value));
		return super.multiplyBy(other);
	}
}
