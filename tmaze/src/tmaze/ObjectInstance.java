package tmaze;

public interface ObjectInstance extends StateInterface{
	String className();
	String getName();
	ObjectInstance copyWithName(String objectName);
}
