package tmaze;


public class SimpleHashableStateFactory implements HashableStateFactory {
	protected boolean identifierIndependent = true;

	public SimpleHashableStateFactory(){

	}

	public SimpleHashableStateFactory(boolean identifierIndependent){
		this.identifierIndependent = identifierIndependent;
	}
	public boolean objectIdentifierIndependent() {
		return this.identifierIndependent;
	}
	@Override
	public HashableState hashState(State s) {
		return (HashableState)s;
	}

}
