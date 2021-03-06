package tmaze;

public class RewardFunction {
	protected double[][] rewardMatrix;
	protected int width;
	protected int height;
	
	public RewardFunction(int width, int height, double initializingReward){
		this.initialize(width,height, initializingReward);
	}
	public RewardFunction(int width, int height) {
		this.initialize(width,height, 0);
	}
	protected void initialize(int width, int height, double initializingReward) {
		this.rewardMatrix = new double[width][height];
		this.width = width;
		this.height = height;
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				
			}
		}
	}
	public double[][] getRewardMatrix() {
		return this.rewardMatrix;
	}
	public void setReward(int x, int y, double r) {
		this.rewardMatrix[x][y] = r;
	}
	public double getRewardForTransitionsTo(int x, int y) {
		return this.rewardMatrix[x][y];
	}
	public double reward(State previous, Action a, State next) {
		int x = ((State)previous).agent.x;
		int y = ((State)previous).agent.y;
		if(x>= this.width || x < 0 || y >= this.height || y < 0) {
			throw new RuntimeException("Agent transitioned to position that is outside of established bounds");
		}
		double r = this.rewardMatrix[x][y];
		return r;
	}
}
