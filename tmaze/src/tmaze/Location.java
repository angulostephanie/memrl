package tmaze;


import java.util.Arrays;
import java.util.List;

public class Location {
	public int x;
	public int y;
	public int type;

	protected String name;
	
	public List<Object> keys = Arrays.<Object>asList("x", "y", "type");
	
	public Location(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	public String getName() {
		return name;
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
		else if(key.equals("type")){
			return type;
		}

		throw new RuntimeException("Unknown key " + key);
	}
}
