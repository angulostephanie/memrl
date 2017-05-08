package tmaze;

public class Action {
	protected String name;
	
	public Action() {
		
	}
	public Action(String name) {
		this.name = name;
	}
	
	public String getActionName() {
		return  name;
	}
	public Action copy() {
		return new Action(name);
	}
}
