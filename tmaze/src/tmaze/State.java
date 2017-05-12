package tmaze;

import java.util.ArrayList;
import java.util.List;


public class State {
	public Agent agent;
	public State s;
	public List<Location> locations = new ArrayList<Location>();
	public double p; // probability
	public State(Agent agent, List<Location> locations) {
		this.agent = agent;
		this.locations = locations;
	}
	public State(Agent agent) {
		this.agent = agent;
	}
	public State(int x, int y) {
		this(new Agent(x,y));
	}
	public State(State s, double p) {
		this.s = s;
		this.p = p;
	}
	public State copy() {
		return new State(agent, locations);
	}
	public Agent updateAgent(){
		this.agent = agent.copy();
		return agent;
	}
	public List<Location> updateLocations() {
		this.locations = new ArrayList<Location>(locations);
		return locations;
	} 
}

