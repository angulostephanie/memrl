package tmaze;


//import java.util.Arrays;

public class TMazeTester {
	protected static int[][] tmazeMap = new int[][] {{1,0,1},{0,0,0}};
	/*
	 TMaze Domain:
	 (0,0) (0,1) (0,2)
	 (1,0) (1,1) (1,2)
	 
	 (0,0) (0,2) are "walls", or impossible locations for the agent to move.
	 
	 */
	
	public static void main(String[] args) {
		Domain tmazeGrid = new Domain(tmazeMap);
		TerminalFunction endStates = new TerminalFunction();
		RewardFunction rewards = new RewardFunction(3,2);
		Agent a = new Agent(0,1);
		State s = new State(a);
		
		rewards.setReward(1,0,10);
		endStates.markAsTerminalPosition(1, 0); //A
		endStates.markAsTerminalPosition(1,2); //
		
		tmazeGrid.move(s, 1, 0);
		System.out.println(s.agent.getX());
		System.out.println(s.agent.getY());
		
		tmazeGrid.move(s, 1, 0);
		System.out.println(s.agent.getX());
		System.out.println(s.agent.getY());
		tmazeGrid.move(s, 1, 0);
	}
}
