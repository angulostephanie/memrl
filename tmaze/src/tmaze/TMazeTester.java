package tmaze;

public class TMazeTester {
	protected static int[][] tmazeMap = new int[][] {{1,0,1},{0,0,0}};
	
	public static void main(String[] args) {
		Domain tmazeGrid = new Domain(tmazeMap);
		Agent a = new Agent(0,1);
		State s = new State(a);
		tmazeGrid.move(s, 1, 0);
		System.out.println(a.getCurrentLocation());
	}
}
