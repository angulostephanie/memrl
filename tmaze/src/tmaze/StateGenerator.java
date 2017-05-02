package tmaze;

public class StateGenerator {
	protected State src;
	public StateGenerator(State src) {
		this.src = src;
	}
	public State generateState() {
		return src.copy();
	}
}
