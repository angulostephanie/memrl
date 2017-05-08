package tmaze;

import java.util.List;

public interface QProvider {
	List<QValue> qValues(State s);
	
	class Helper {
		private Helper() {
			
		}
		public static double maxQ(QProvider qSource, State s) {
			List<QValue> qs = qSource.qValues(s);
			if(qs.isEmpty()) {
				return 0;
			}
			double max = Double.NEGATIVE_INFINITY;
			for(QValue q: qs) {
				max = Math.max(q.q, max);
			}
			return max;
		}
		public static double policyValue() {
			double expectedValue = 0;
			//add 
			
			return expectedValue;
		}
	}
}
