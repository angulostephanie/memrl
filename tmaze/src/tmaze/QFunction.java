package tmaze;

public class QFunction {
	public double value = 0;
	
	public QFunction() {
		
	} 
	public QFunction(double value) {
		this.value = value;
	}
	public double value(State s) {
		return value;
	}
	public double qValue(State s, Action a) {
		return value;
	}
}
