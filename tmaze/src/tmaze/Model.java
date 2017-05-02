package tmaze;

public interface Model {
	EnvironmentOutcome sample(State s, Action a);
	boolean terminal(State s);
}
