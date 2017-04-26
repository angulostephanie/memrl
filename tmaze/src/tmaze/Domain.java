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
	
	public boolean invalidCell(int x, int y){
		if(this.map[x][y] == 0) {
			return false;
		}
		return true;
	}
	
	protected static int [] movementDirectionFromIndex(int i){
		int [] result = null;
		switch (i) {
			case 0:
				result = new int[]{0,1};
				break;
			case 1:
				result = new int[]{0,-1};
				break;
			case 2:
				result = new int[]{1,0};
				break;
			case 3:
				result = new int[]{-1,0};
				break;
			default:
				break;
		}

		return result;
	}
	
	protected int actionInd(String name){
		if(name.equals(ACTION_NORTH)){
			return 0;
		}
		else if(name.equals(ACTION_SOUTH)){
			return 1;
		}
		else if(name.equals(ACTION_EAST)){
			return 2;
		}
		else if(name.equals(ACTION_WEST)){
			return 3;
		}
		throw new RuntimeException("Unknown action " + name);
	}
	
	public State move(State s, int xd, int yd){

		State gws = (State)s;

		int ax = gws.agent.x;
		int ay = gws.agent.y;

		int nx = ax+xd;
		int ny = ay+yd;

		// do not change position
		if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length || map[nx][ny] == 1 ||
				(xd > 0 && (map[ax][ay] == 3 || map[ax][ay] == 4)) || (xd < 0 && (map[nx][ny] == 3 || map[nx][ny] == 4)) ||
				(yd > 0 && (map[ax][ay] == 2 || map[ax][ay] == 4)) || (yd < 0 && (map[nx][ny] == 2 || map[nx][ny] == 4)) ){
			nx = ax;
			ny = ay;
			//throw new RuntimeException("Obstacle! Cannot move in this direction");
		}

		Agent nagent = gws.updateAgent();
		nagent.x = nx;
		nagent.y = ny;
		

		return s;
	}
}
