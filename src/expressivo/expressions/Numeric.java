package expressivo.expressions;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Class representing an integer or floating-point numeric constant.
 * @author nick
 *
 */
class Numeric extends Primitive {
	
	private final Number value;
	
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
		try {
			NumberFormat nf = NumberFormat.getInstance();
			this.value = nf.parse(val);

		} catch (ParseException ex) {
			throw new IllegalArgumentException(
					"Could not parse '" + val + "' into a numeric");
		}
	}

	Numeric(Number val) {
		this(val.toString());
	}
	
	@Override
	public Expression differentiate(Variable variable) {
		throw new UnsupportedOperationException("todo");
	}

	@Override
	public String toString() {
		return value.toString(); 
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Numeric) {
			return this.value.equals(((Numeric)other).value);
		}
		return super.equals(other);
	}

}
