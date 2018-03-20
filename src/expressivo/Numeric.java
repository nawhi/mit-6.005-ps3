package expressivo;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Class representing an integer or floating-point numeric constant.
 * @author nick
 *
 */
public class Numeric implements Expression {
	
	private final Number value;
	
	// Representation invariant:
	//   true
	// Safety from rep exposure:
	//   private fields are final, and getter methods
	//	 always return copies (not references)
	
	/**
	 * Create a new instance of Numeric from a supplied string
	 * @param val A string containing a parseable integer or floating-point
	 * 			  number
	 * @throws IllegalArgumentException if the supplied string cannot
	 *         be parsed into an integer or floating-point number
	 */
	public Numeric(String val) {
		// TODO: is there a better way
		
		try {
			NumberFormat nf = NumberFormat.getInstance();
			this.value = nf.parse(val);
		} catch (ParseException ex) {
			// Unchecked is fine - this should never happen
			throw new IllegalArgumentException("Could not parse numeric: " + val);
		}
	}
	
	public Number getValue() {
		return value.doubleValue();
	}

	@Override public String toString() {
		return value.toString(); 
	}
	
	@Override public int hashCode() {
		throw new RuntimeException("not implemented");
	}
	
	@Override public boolean equals(Object other) {
		if (other instanceof Numeric) {
			// Hard to see how to do this without
			// a massive messy disambiguation
			throw new RuntimeException("not implemented");
		}
		return super.equals(other);
	}
}
