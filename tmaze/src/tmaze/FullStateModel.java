package tmaze;

import java.util.Arrays;
import java.util.List;


public interface FullStateModel extends SampleStateModel{
	List<State> stateTransitions(State s, Action a);

	class Helper{

		public static List<State> deterministicTransition(SampleStateModel model, State s, Action a){
			return Arrays.asList(new State(model.sample(s, a), 1.));
		}

		public static State sampleByEnumeration(FullStateModel model, State s, Action a){

			List<State> tps = model.stateTransitions(s, a);
			double roll = RandomFactory.getMapped(0).nextDouble();
			double sum = 0;
			for(State tp : tps){
				sum += tp.p;
				if(roll < sum){
					return tp.s;
				}
			}

			throw new RuntimeException("Transition probabilities did not sum to one, they summed to " + sum);

		}
	}

}
