package tmaze;

public class Domain {
	public static final String VAR_X = "x";
	public static final String VAR_Y = "y";
	public static final String VAR_TYPE = "type";
	public static final String CLASS_AGENT = "agent";
	public static final String CLASS_LOCATION = "location";
	public static final String ACTION_NORTH = "up";
	public static final String ACTION_SOUTH = "down";
	public static final String ACTION_EAST = "right";
	public static final String ACTION_WEST = "left";
	protected int width;
	protected int height;
	protected int numLocationTypes = 1;
	protected int [][] map;
	
	public Domain(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public Domain(int [][] map) {
		this.map = map;
	}
	
	public void setMap(int [][] map){
		this.width = map.length;
		this.height = map[0].length;
		this.map = map.clone();
	}
	public void setNumberOfLocationTypes(int numLocationTypes){
		this.numLocationTypes = numLocationTypes;
	}
}
