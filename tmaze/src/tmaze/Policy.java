package tmaze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.management.RuntimeErrorException;

public class Policy {
	protected Random rand;
	protected QProvider qPlanner;
	
	public Policy() {
		qPlanner = null;
		rand = RandomFactory.getMapped(0);
	}
	public Policy(QProvider planner) {
		qPlanner = planner;
		rand = RandomFactory.getMapped(0);
	}
	public void setSolver(MDPSolver solver) {
		if(!(solver instanceof QProvider)) {
			throw new RuntimeErrorException(new Error("Planner is not a QComputablePlanner"));
		}
		this.qPlanner = (QProvider) solver;
	}
	public double actionProb(State s, Action a) {
		List<ActionProb> probs = policyDistribution(s);
		if(probs == null || probs.isEmpty()) {
			throw new RuntimeException("No actions taken");
		} 
		for(ActionProb ap: probs) {
			if(ap.consideredA.equals(a)) {
				return ap.pSelection;
			}
		}
		return 0;
	}
	public boolean definedFor(State s) {
		return true;
	}
	public Action action(State s) {
		List<QValue> qValues = this.qPlanner.qValues(s);
		List<QValue> maxActions = new ArrayList<QValue>();
		maxActions.add(qValues.get(0));
		double maxQ = qValues.get(0).q;
		for(int i = 1; i < qValues.size(); i++) {
				QValue q = qValues.get(i);
				if(q.q == maxQ) {
					maxActions.add(q);
				} else if(q.q > maxQ) {
					maxActions.clear();
					maxActions.add(q);
					maxQ = q.q;
				}
		}
		int selected = rand.nextInt(maxActions.size());
		Action srcA = maxActions.get(selected).action;
		return srcA;
	}
	public List<ActionProb> policyDistribution(State s) {
		List<QValue> qValues = this.qPlanner.qValues(s);
		int numMax = 1;
		double maxQ = qValues.get(0).q;
		for(int i = 1; i < qValues.size(); i++) {
			QValue q = qValues.get(i);
			if(q.q == maxQ) {
				numMax++;
			} else if(q.q > maxQ) {
				numMax = 1;
				maxQ = q.q;
			}
		}
		List<ActionProb> res = new ArrayList<ActionProb>();
		double uniformMax = 1./(double)numMax;
		for(int i = 0; i< qValues.size(); i++) {
			QValue q = qValues.get(i);
			double p = 0;
			if(q.q == maxQ) {
				p = uniformMax;
			}
			ActionProb ap = new ActionProb(q.action, p);
			res.add(ap);
		}
		
		return res;
		
	}
}
