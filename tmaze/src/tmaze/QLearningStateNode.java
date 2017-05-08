package tmaze;

import java.util.ArrayList;
import java.util.List;

public class QLearningStateNode {
	public HashableState s;
	public List<QValue> qEntry;
	
	public QLearningStateNode() {
		
	}
	public QLearningStateNode(HashableState s) {
		this.s = s;
		qEntry = new ArrayList<QValue>();
	}
	public void addQValue(Action a, double q) {
		QValue qv = new QValue(s.s(), a, q);
		qEntry.add(qv);
	}
	
}
