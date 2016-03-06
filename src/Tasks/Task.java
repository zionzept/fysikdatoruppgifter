package Tasks;

import java.util.LinkedList;

import diagram.Diagram;
import diagram.Plot;

public abstract class Task {
	private double to;
	private double from;
	protected double step;
	private LinkedList<Plot> plots;
	private Diagram diagram;
	
	public Task(double from, double to, int points, Diagram diagram){
		this.from = from;
		this.to = to;
		this.step =  (to - from) / points;
		this.plots = new LinkedList<Plot>();
		this.diagram = diagram;
	}
	
	public LinkedList<Plot> getPlots(){
		return plots;
	}
	
	public Diagram getDiagram(){
		return diagram;
	}
	
	public void compute() {
		double x = from;
		while (x <= to) {
			compute(x);
			x += step;
		}
		finish();
	}
	
	public abstract void compute(double x);
	public abstract void finish();
}
