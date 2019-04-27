package expressivo.expressions;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

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
		throw new UnsupportedOperationException("TODO");
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

	Expression plus(Numeric other) {
		return new Numeric(this.value.add(other.value));
	}

	Expression times(Numeric other) {
		return new Numeric(this.value.multiply(other.value));
	}
}
