package tmaze;

public class Location {
	public int x;
	public int y;
	public int type;

	protected String name;
	
	public Location(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
}
