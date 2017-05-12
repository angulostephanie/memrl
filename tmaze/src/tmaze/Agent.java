package tmaze;

import java.util.Arrays;
import java.util.List;


public class Agent implements ObjectInstance{
	public int x;
	public int y;
	public String name;
	
	private final static List<Object> keys = Arrays.<Object>asList("x", "y");
	
	public Agent() {
		this.name = "agent";
	}
	public Agent(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String getName() {
		return name;
	}
	@Override
	public String className() {
		return getName();
	}
	
	public List<Object> variableKeys() {
		return keys;
	}

	public Object get(Object variableKey) {
		if(!(variableKey instanceof String)){
			throw new RuntimeException("GridAgent variable key must be a string");
		}

		String key = (String)variableKey;
		if(key.equals("x")){
			return x;
		}
		else if(key.equals("y")){
			return y;
		}

		throw new RuntimeException("Unknown key " + key);
	}


	@Override
	public ObjectInstance copyWithName(String objectName) {
		Agent nagent = this.copy();
		nagent.name = objectName;
		return nagent;
	}
	@Override
	public State copy() {
		
		return null;
	}

	public Agent copy() {
		return new Agent(x,y);
	}
	
	
}
