package expressivo.expressions;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Class representing an integer or floating-point numeric constant.
 * @author nick
 *
 */
class Numeric extends Primitive {
	static final Numeric ZERO = new Numeric(new BigDecimal(0));
	static final Numeric ONE = new Numeric(new BigDecimal(1));

	private final BigDecimal value;
	
	// Representation invariant:
	//   true
	// Safety from rep exposure:
	//   private fields are final
	
	/**
	 * Create a new instance of Numeric from a supplied string
	 * @param val A string containing a parseable integer or floating-point
	 * 			  number
	 * @throws IllegalArgumentException if the supplied string cannot
	 *         be parsed into an integer or floating-point number
	 */
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

	Expression plus(Numeric addend) {
		return new Numeric(this.value.add(addend.value));
	}
}
