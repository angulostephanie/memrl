package tmaze;


public class EnvironmentOptionOutcome extends EnvironmentOutcome{
	public double discount;
	public Episode episode;

	public EnvironmentOptionOutcome(State s, Action a, State sp, double r, boolean terminated, double discountFactor, Episode episode) {
		super(s, a, sp, r, terminated);
		this.discount = Math.pow(discountFactor, episode.actionSequence.size());
		this.episode = episode;
	}

	public int numSteps(){
		return episode.actionSequence.size();
	}
}
