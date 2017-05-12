package tmaze;

import java.util.List;

public interface StateInterface {
	List<Object> variableKeys();
	Object get(Object variableKey);
	State copy();
}
