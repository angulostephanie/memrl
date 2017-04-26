package tmaze;

import java.util.ArrayList;
import java.util.List;



public class State {
	public Agent agent;
	public List<Location> locations = new ArrayList<Location>();
	
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

