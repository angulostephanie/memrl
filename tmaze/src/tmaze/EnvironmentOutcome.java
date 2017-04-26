package tmaze;

public class EnvironmentOutcome {

	public State previous;
	public Action action;
	public State next;
	public double r;
	public boolean terminated;
	
	public EnvironmentOutcome(State previous, Action action, State next, double r, boolean terminated) {
		this.previous = previous;
		this.action = action;
		this.next = next;
		this.r = r;
		this.terminated = terminated;
	}
	
}
