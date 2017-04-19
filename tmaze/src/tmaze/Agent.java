package tmaze;

public class Agent {
	public int x;
	public int y;
	
	public Agent(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int[][] getCurrentLocation() {
		
		return new int [x][y];
	}
}
