package tmaze;

import java.util.List;

public interface Model {
	EnvironmentOutcome sample(State s, Action a);
	boolean terminal(State s);
	List<State> stateTransitions(State s, Action a);
}
