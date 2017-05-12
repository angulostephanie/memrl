package tmaze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Domain {
	public static final String VAR_X = "x";
	public static final String VAR_Y = "y";
	public static final String VAR_TYPE = "type";
	public static final String CLASS_AGENT = "agent";
	public static final String CLASS_LOCATION = "location";
	public static final String ACTION_NORTH = "up";
	public static final String ACTION_SOUTH = "down";
	public static final String ACTION_EAST = "right";
	public static final String ACTION_WEST = "left";
	protected RewardFunction rf;
	protected TerminalFunction tf;
	protected Model model;
	protected Domain domain;
	protected int width;
	protected int height;
	protected int numLocationTypes = 1;
	protected int [][] map;
	protected double[][] transitionDynamics;
	protected List<Action> actionTypes = new ArrayList<Action>();
	protected Map<String, Action> actionMap = new HashMap<String, Action>();

	public Domain(int width, int height) {
		this.width = width;
		this.height = height;
		this.setDeterministicTransitionDynamics();
	}
	public Domain(int [][] map) {
		this.map = map;
		this.setDeterministicTransitionDynamics();
	}

	public void setMap(int [][] map){
		this.width = map.length;
		this.height = map[0].length;
		this.map = map.clone();
	}
	
	
	public void setDeterministicTransitionDynamics(){
		int na = 4;
		transitionDynamics = new double[na][na];
		for(int i = 0; i < na; i++){
			for(int j = 0; j < na; j++){
				if(i != j){
					transitionDynamics[i][j] = 0.;
				}
				else{
					transitionDynamics[i][j] = 1.;
				}
			}
		}
	}
	
	public void setProbSucceedTransitionDynamics(double probSucceed){
		int na = 4;
		double pAlt = (1.-probSucceed)/3.;
		transitionDynamics = new double[na][na];
		for(int i = 0; i < na; i++){
			for(int j = 0; j < na; j++){
				if(i != j){
					transitionDynamics[i][j] = pAlt;
				}
				else{
					transitionDynamics[i][j] = probSucceed;
				}
			}
		}
	}
	
	public void setTransitionDynamics(double [][] transitionDynamics){
		this.transitionDynamics = transitionDynamics.clone();
	}
	
	public void setNumberOfLocationTypes(int numLocationTypes){
		this.numLocationTypes = numLocationTypes;
	}
	
	public void setRf(RewardFunction rf) {
		this.rf = rf;
	}
	
	public void setTf(TerminalFunction tf) {
		this.tf = tf;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	
	public boolean invalidCell(int x, int y){
		if(this.map[x][y] == 0) {
			return false;
		}
		return true;
	}
	public int [][] getMap(){
		int [][] cmap = new int[this.map.length][this.map[0].length];
		for(int i = 0; i < this.map.length; i++){
			for(int j = 0; j < this.map[0].length; j++){
				cmap[i][j] = this.map[i][j];
			}
		}
		return cmap;
	}
	public double [][] getTransitionDynamics(){
		double [][] copy = new double[transitionDynamics.length][transitionDynamics[0].length];
		for(int i = 0; i < transitionDynamics.length; i++){
			for(int j = 0; j < transitionDynamics[0].length; j++){
				copy[i][j] = transitionDynamics[i][j];
			}
		}
		return copy;
	}
	protected static int [] movementDirectionFromIndex(int i){
		int [] result = null;
		switch (i) {
			case 0:
				result = new int[]{-1,0};
				break;
			case 1:
				result = new int[]{1,0};
				break;
			case 2:
				result = new int[]{0,-1};
				break;
			case 3:
				result = new int[]{0,1};
				break;
			default:
				break;
		}

		return result;
	}
	
	public List<Action> getActionTypes() {
		return new ArrayList <Action>(actionTypes);
	}
	
	public Model getModel() {
		return model;
	}
	public RewardFunction getRf() {
		return rf;
	}
	public TerminalFunction getTf() {
		return tf;
	}
	
	public Action getAction(String name) {
		return actionMap.get(name);
	}
	
	public Domain addActionType(Action act){
		if(!actionMap.containsKey(act.typeName())) {
			actionTypes.add(act);
			actionMap.put(act.typeName(), act);
		}
		return this;
	}
	public Domain addActionTypes(Action...actions){
		for(Action action: actions) {
			this.addActionType(action);
		}
		return this;
	}
	public Domain setActionTypes(List<Action> actions) {
		this.actionTypes.clear();
		this.actionMap.clear();
		for(Action at: actions) {
			this.addActionType(at);
		}
		return this;
	}
	public Domain setActionTypes(Action...actions) {
		return this.setActionTypes(Arrays.asList(actions));
	}
	public Domain clearActionTypes() {
		this.actionMap.clear();
		this.actionTypes.clear();
		return this;
	}
	public Domain generateDomain() {
		Domain test = new Domain(getMap());
		int[][] cmap = this.getMap();
		TMazeModel tmodel = new TMazeModel(cmap, getTransitionDynamics());
		RewardFunction rf = this.rf;
		TerminalFunction tf = this.tf;
		
		if(rf == null) 
			throw new RuntimeException("Please create a reward function");
		if(tf == null)
			throw new RuntimeException("Please create a terminal function");
		
		FactoredModel fmodel = new FactoredModel(tmodel, rf, tf);
		test.setModel(fmodel); //<---->
		
		test.addActionTypes(new Action(ACTION_NORTH), new Action(ACTION_SOUTH), new Action(ACTION_EAST), new Action(ACTION_WEST));
		return test;
	}
	
	
	
	public static class TMazeModel implements FullStateModel{

		int [][] map;

		protected double[][] transitionDynamics;

		protected Random rand = RandomFactory.getMapped(0);


		public TMazeModel(int[][] map, double[][] transitionDynamics) {
			this.map = map;
			this.transitionDynamics = transitionDynamics;
		}

		public List<State> stateTransitions(State s, Action a) {

			double [] directionProbs = transitionDynamics[actionInd(a.typeName())];

			List <State> transitions = new ArrayList<State>();
			for(int i = 0; i < directionProbs.length; i++){
				double p = directionProbs[i];
				if(p == 0.){
					continue; //cannot transition in this direction
				}
				State ns = s.copy();
				int [] dcomps = movementDirectionFromIndex(i);
				ns = move(ns, dcomps[0], dcomps[1]);

				//make sure this direction doesn't actually stay in the same place and replicate another no-op
				boolean isNew = true;
				for(State tp : transitions){
					if(tp.s.equals(ns)){
						isNew = false;
						tp.p += p;
						break;
					}
				}

				if(isNew){
					State tp = new State(ns, p);
					transitions.add(tp);
				}
			}
			return transitions;
		}

		public State sample(State s, Action a) {

			s = s.copy();

			double [] directionProbs = transitionDynamics[actionInd(a.typeName())];
			double roll = rand.nextDouble();
			double curSum = 0.;
			int dir = 0;
			for(int i = 0; i < directionProbs.length; i++){
				curSum += directionProbs[i];
				if(roll < curSum){
					dir = i;
					break;
				}
			}

			int [] dcomps = movementDirectionFromIndex(dir);
			return move(s, dcomps[0], dcomps[1]);

		}

		protected State move(State s, int xd, int yd){

			State gws = (State)s;

			int ax = gws.agent.x;
			int ay = gws.agent.y;

			int nx = ax+xd;
			int ny = ay+yd;

			//hit wall, so do not change position
			if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length || map[nx][ny] == 1 ||
					(xd > 0 && (map[ax][ay] == 3 || map[ax][ay] == 4)) || (xd < 0 && (map[nx][ny] == 3 || map[nx][ny] == 4)) ||
					(yd > 0 && (map[ax][ay] == 2 || map[ax][ay] == 4)) || (yd < 0 && (map[nx][ny] == 2 || map[nx][ny] == 4)) ){
				nx = ax;
				ny = ay;
			}

			Agent nagent = gws.updateAgent();
			nagent.x = nx;
			nagent.y = ny;

			return s;
		}


		protected int actionInd(String name){
			if(name.equals(ACTION_NORTH)){
				return 0;
			}
			else if(name.equals(ACTION_SOUTH)){
				return 1;
			}
			else if(name.equals(ACTION_EAST)){
				return 2;
			}
			else if(name.equals(ACTION_WEST)){
				return 3;
			}
			throw new RuntimeException("Unknown action " + name);
		}

	
		
	}
}
