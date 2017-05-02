package tmaze;

public class Agent {
	public int x;
	public int y;
	public String name;
	public Agent() {
		this.name = "agent";
	}
	public Agent(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Agent copy() {
		return new Agent(x,y);
	}
	
}
