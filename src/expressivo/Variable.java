package expressivo;

public class Variable extends Primitive {

	// Representation invariant:
	//   true
	// Safety from rep exposure:
	// immutable type and use of immutable String 
	// internally (means copies are always returned)
	
	private final String ident;
	
	public Variable(String ident) {
		this.ident = ident;
	}
	
	public String toString() {
		return ident;
	}

}
