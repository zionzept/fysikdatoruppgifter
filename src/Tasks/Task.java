package Tasks;

import java.util.LinkedList;

import diagram.Diagram;
import diagram.Plot;

public class Task {
	private double to;
	private double from;
	protected double scaling;
	private LinkedList<Plot> plots;
	private Diagram diagram;
	
	public Task(double from, double to, int points, Diagram diagram){
		this.from = from;
		this.to = to;
		this.scaling =  (to - from) / points;
		this.plots = new LinkedList<Plot>();
		this.diagram = diagram;
	}
	
	public LinkedList<Plot> getPlots(){
		return plots;
	}
	
	public Diagram getDiagram(){
		return diagram;
	}
}
