package tmaze;

public class LearningRate {
	public double learningRate = 0.1;
	
	public LearningRate() {
		
	}
	public LearningRate(double learningRate) {
		this.learningRate = learningRate;
	}
	public double peekAtLearningRate(State state, Action action) {
		return this.learningRate;
	}
	public void resetDecay() {
		
	}
}
