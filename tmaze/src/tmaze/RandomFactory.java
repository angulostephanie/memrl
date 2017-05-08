package tmaze;

import java.util.*;

public class RandomFactory {
	private static RandomFactory factory = new RandomFactory();
	Random defaultRandom;
	Map<Integer, Random> intMapped;
	Map<String, Random> stringMapped;
	
	public RandomFactory() {
		defaultRandom = null;
		intMapped = new HashMap<Integer, Random>();
		stringMapped = new HashMap<String, Random>();
	}
	public static Random getMapped(int id) {
		return factory.ingetMapped(id);
	}
	public Random ingetMapped(int id) {
		Random r = intMapped.get(id);
		if(r != null) {
			return r;
		} else {
			r = new Random();
			intMapped.put(id, r);
		}
		return r;
	}
	
}
