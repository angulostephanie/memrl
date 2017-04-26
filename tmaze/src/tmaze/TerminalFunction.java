package tmaze;

import java.util.HashSet;
import java.util.Set;

public class TerminalFunction {
	protected Set<IntPair> terminalPositions = new HashSet<TerminalFunction.IntPair>();
	
	public TerminalFunction(int x, int y) {
		this.terminalPositions.add(new IntPair(x,y));
	}
	public TerminalFunction(IntPair ...terminalPositions) {
		for(IntPair i: terminalPositions) {
			this.terminalPositions.add(i);
		}
	}
	public void markAsTerminalPosition(int x, int y) {
		this.terminalPositions.add(new IntPair(x,y));
	}
	public void unmarkTerminalPosition(int x, int y) {
		this.terminalPositions.remove(new IntPair(x,y));
	}
	public boolean isTerminalPosition(int x, int y) {
		return this.terminalPositions.contains(new IntPair(x,y));
	}
	public boolean isTerminal(State s) {
		int x = ((State)s).agent.x;
		int y = ((State)s).agent.y;
		return this.terminalPositions.contains(new IntPair(x,y));
	}
	
	public class IntPair {
		public int x;
		public int y;
		public IntPair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			return this.x + 31*this.y;
		}
		@Override
		public boolean equals(Object other) {
			if(!(other instanceof IntPair)) {
				return false;
			}
			IntPair o = (IntPair)other;
			return this.x == o.x && this.y == o.y;
		}
	}
	

}

