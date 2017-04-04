package tmaze;

public class State {
	public Agent agent;
	
	public State(Agent agent) {
		this.agent = agent;
	}
	public State(int x, int y) {
		this(new Agent(x,y));
	}
}

