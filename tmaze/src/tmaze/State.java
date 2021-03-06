package tmaze;

import java.util.ArrayList;
import java.util.List;

public class State implements StateInterface{
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
	
	public List<Object> variableKeys() {
		return State.flatStateKeys(this);
	}
	
	public Object get(Object variableKey) {
		VariableKey key = State.generateKey(variableKey);
		if(key.obName.equals(agent.getName())){
			return agent.get(key.obVarKey);
		}
		int ind = this.locationInd(key.obName);
		if(ind == -1){
			throw new RuntimeException("Cannot find object " + key.obName);
		}
		return locations.get(ind).get(key.obVarKey);
	}
	
	public static List<Object> flatStateKeys(State s){
		List<Object> flatKeys = new ArrayList<Object>();
		for(ObjectInstance o : s.objects()){
			List<Object> keys = o.variableKeys();
			for(Object key : keys){
				VariableKey fkey = new VariableKey(o.name(), key);
				flatKeys.add(fkey);
			}
		}
		return flatKeys;
	}
	public static VariableKey generateKey(Object key){
		if(key instanceof VariableKey){
			return (VariableKey)key;
		}
		else if(key instanceof String){
			return new VariableKey((String)key);
		}
		throw new RuntimeException("Cannot generate this key type --- " + key.getClass().getName());
	}
	protected int locationInd(String oname){
		int ind = -1;
		for(int i = 0; i < locations.size(); i++){
			if(locations.get(i).getName().equals(oname)){
				ind = i;
				break;
			}
		}
		return ind;
	}
	public ObjectInstance object(String oname) {
		if(oname.equals(agent.getName())){
			return agent;
		}
		int ind = this.locationInd(oname);
		if(ind != -1){
			return locations.get(ind);
		}
		return null;
	}

	public List<ObjectInstance> objects() {
		List<ObjectInstance> obs = new ArrayList<ObjectInstance>(1+locations.size());
		obs.add(agent);
		obs.addAll(locations);
		
		return obs;
	}
}

