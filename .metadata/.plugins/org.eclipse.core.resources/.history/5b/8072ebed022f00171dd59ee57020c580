package tmaze;

import java.util.ArrayList;
import java.util.List;


public class Episode {

	public List<State> stateSequence = new ArrayList<State>();
	public List<Action> actionSequence = new ArrayList<Action>();
	public List<Double>	rewardSequence = new ArrayList<Double>();
	
	public Episode() {
		
	}
	
	public Episode(State initState) {
		this.addInitState(initState);
	}
	
	public void addInitState(State initState) {
		this.stateSequence.add(initState);
	}
	
	public Action getAction(int time) {
		//add in runtime exceptions 
		return actionSequence.get(time);
	}
	
	public State getState(int time) {
		//add in runtime exceptions
		return stateSequence.get(time);
	}
	
	public double reward(int time) {
		return rewardSequence.get(time-1);
	}
	
	public int numTimeSteps(){
		return stateSequence.size(); //initial state and terminal state 
	}
	
}
