package tmaze;

public class QValue {
	public Action action;
	public State state;
	public double q;
	
	public QValue() {
		
	}
	public QValue(State state, Action action, double q) {
		this.state = state;
		this.action = action;
		this.q = q;
	}
	public QValue(QValue src) {
		this.state = src.state.copy();
		this.action = src.action.copy();
		this.q = src.q;
	}
}
