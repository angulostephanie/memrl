package tmaze;

//import java.util.LinkedList;
//import java.util.List;

public class Environment {
	//protected List<EnvironmentObserver> observers = new LinkedList<EnvironmentObserver>();
	protected State currentState;
	protected StateGenerator stateGenerator;
	protected Model model;
	protected boolean terminated = false;
	protected double lastReward = 0;
	protected boolean allowActionFromTerminalStates = false;
	// adding interface might be useful?
	public Environment(Domain domain, State initState) {
		if(domain.getModel() == null) {
			throw new RuntimeException("Environment requires a Domain with a model, but the input domain does not have one.");
		}
		this.model = domain.getModel();
		this.currentState = initState;
		this.stateGenerator = new StateGenerator(initState);
	}
//	public void addObservers(EnvironmentObserver... observers) {
//		for(EnvironmentObserver o : observers){
//			this.observers.add(o);
//		}
//	}
//
//	public void clearAllObservers() {
//		this.observers.clear();
//	}
//
//	public void removeObservers(EnvironmentObserver... observers) {
//		for(EnvironmentObserver o : observers){
//			this.observers.remove(o);
//		}
//	}

	public void setCurrentStateTo(State s) {
		if(this.stateGenerator == null) {
			this.stateGenerator = new StateGenerator(s);
		}
		this.currentState = s;
	}
	public void setStateGenerator(StateGenerator stateGenerator) {
		this.stateGenerator = stateGenerator;
	}
	
	public void resetEnvironment() {
		this.lastReward = 0;
		this.terminated = false;
		this.currentState = stateGenerator.generateState();
//		for(EnvironmentObserver observer : this.observers){
//			observer.observeEnvironmentReset(this);
//		}
	}
	
	public boolean isInTerminalState() {
		return this.terminated;
	}
	
	public double lastReward() {
		return this.lastReward;
	}
	
	public State currentObservation() {
		return this.currentState.copy();
	}
	
	public StateGenerator getStateGenerator() {
		return stateGenerator;
	}
	
	public EnvironmentOutcome executeAction(Action a) {
//		for(EnvironmentObserver observer : this.observers){
//			observer.observeEnvironmentActionInitiation(this.currentObservation(), a);
//		}
		EnvironmentOutcome eo;
		if(this.allowActionFromTerminalStates || !this.isInTerminalState()) {
			eo = model.sample(this.currentState, a);
		} else {
			eo = new EnvironmentOutcome(this.currentState, a, this.currentState.copy(), 0, true);
		}
		
		this.lastReward = eo.r;
		this.terminated = eo.terminated;
		this.currentState = eo.previous;
		
//		for(EnvironmentObserver observer : this.observers){
//			observer.observeEnvironmentInteraction(eo);
//		}
		return eo;
	}
	
//	public List<EnvironmentObserver> observers() {
//		return this.observers;
//	}
}
