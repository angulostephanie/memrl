package tmaze;

import java.util.ArrayList;
import java.util.List;


public class Action {
	public String name;
	public Action action;
	public List<Action> allActions;
	
	public Action(String name) {
		this.name = name;
		//this(new Action(name));
	}
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Action that = (Action) o;
		return name != null ? name.equals(that.name) : that.name == null;
	}
	
	public String typeName() {
		return name;
	}
	public Action copy() {
		return new Action(name);
	}
	public Action associatedAction(String strRep) {
		return action;
	}
	public List<Action> allApplicableActions(State s) {
		return allActions;
	}
	
	public static List<Action> allApplicableActionsForTypes(List<Action> actionTypes, State s){
		List<Action> res = new ArrayList<Action>();
		for(Action a : actionTypes){
			res.addAll(a.allApplicableActions(s));
		}
		return res;
	}
}
