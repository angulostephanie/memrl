package tmaze;


import java.util.ArrayList;
import java.util.List;

public class FactoredModel implements Model {
	//protected SampleStateModel stateModel;
	protected SampleStateModel stateModel;
	protected RewardFunction rf;
	protected TerminalFunction tf;
	
	public FactoredModel(SampleStateModel stateModel, RewardFunction rf, TerminalFunction tf) {
		this.stateModel = stateModel;
		this.rf = rf;
		this.tf =tf;
	}

	public void setRf(RewardFunction rf) {
		this.rf = rf;
	}
	public void setTf(TerminalFunction tf) {
		this.tf = tf;
	}
	public void setTModel(SampleStateModel stateModel) {
		this.stateModel = stateModel;
	}

	public RewardFunction getRf() {
		return rf;
	}

	public TerminalFunction getTf() {
		return tf;
	}
	
	public List<TransitionProb> transitions(State s, Action a) {

		if(!(this.stateModel instanceof Model)){
			throw new RuntimeException("Factored Model cannot enumerate transition distribution, because the state model does not implement FullStateModel");
		}

		List<State> stps = ((Model)this.stateModel).stateTransitions(s, a);
		List<TransitionProb> tps = new ArrayList<TransitionProb>(stps.size());
		for(State stp : stps){
			double r = this.rf.reward(s, a, stp.s);
			boolean t = this.tf.isTerminal(stp.s);
			TransitionProb tp = new TransitionProb(stp.p, new EnvironmentOutcome(s, a, stp.s, r, t));
			tps.add(tp);
		}

		return tps;
	}
	@Override
	public EnvironmentOutcome sample(State s, Action a) {
		State sprime = this.stateModel.sample(s, a);
		double r = this.rf.reward(s, a, sprime);
		boolean t = this.tf.isTerminal(sprime);

		EnvironmentOutcome eo = new EnvironmentOutcome(s, a, sprime, r, t);

		return eo;
	}

	@Override
	public boolean terminal(State s) {
		return this.tf.isTerminal(s);
	}


	@Override
	public List<State> stateTransitions(State s, Action a) {
		// TODO Auto-generated method stub
		return null;
	}
}
