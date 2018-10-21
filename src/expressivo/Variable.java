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
	
	@Override
	public Expression differentiate(Variable variable) {
		return new Numeric(this.equals(variable) ? 1 : 0);
	}

	@Override public String toString() {
		return ident;
	}
	
	@Override public int hashCode() {
		return ident.hashCode();
	}
	
	@Override public boolean equals(Object other) {
		if (other instanceof Variable)
			return this.ident.equals(((Variable) other).ident);
		return super.equals(other);
	}


}
