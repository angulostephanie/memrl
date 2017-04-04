package tmaze;

public class Environment {
	protected State curState;
	protected boolean terminated = false;
	
	public Environment(Domain domain, State initState) {
		this.curState = initState;
	}
	
	public boolean isInTerminalState() {
		return false;
	}
	public void setCurStateTo(State s) {
		this.curState = s;
	}
	public void resetEnvironment() {
		
	}
	
}
