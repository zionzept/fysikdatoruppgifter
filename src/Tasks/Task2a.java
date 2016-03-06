package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.Plot;
import diagram.Point;

public class Task2a extends Task{
	private LinkedList<Point> points;
	
	private final double n1 = 1; //brytningsindex luft
	private final double n2 = 1.5; //brytningsindex glas;
	private final double R = 0.15; //Krökningsradie, m
	
	public Task2a(double from, double to, int points) {
		super(from, to, points, new Diagram("2a", "Höjd h/cm", "Avstånd f/cm", 2, 4));
		this.points = new LinkedList<>();
	}

	@Override
	public void compute(double x) {		//höjd, m
		double i = Math.asin(x / R);	//beräkning av infallsvinkel
		double r = Math.asin(n1 * Math.sin(i) / n2);	
		double v = Math.PI / 2 - i + r;
		double d = Math.sqrt(R * R - x * x);	
		double f = R - d + x * Math.tan(v);		//beräkning av avståndet f, m
		points.add(new Point(x*100, f*100));	//höjd h och avståndet f till cm
	}

	@Override
	public void finish() {
		Plot p1 = new Plot(points);
		p1.setColor(Color.blue);
		getPlots().add(p1);
	}
}