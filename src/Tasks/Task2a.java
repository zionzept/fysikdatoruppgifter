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
	private final double R = 0.15; //Krï¿½kningsradie, m
	
//	double f0; //avstånd till bildbrännvidd från origo
	public Task2a(double from, double to, int points) {
		super(from, to, points, new Diagram("2a", "höjd h/cm", "avstånd f/cm", 2, 4));
		this.points = new LinkedList<>();
	}

	@Override
	public void compute(double x) {//höjd, m
		
		double i = Math.asin(x / R);
		double r = Math.asin(n1 * Math.sin(i) / n2);
		double v = Math.PI / 2 - i + r;
		double d = Math.sqrt(R * R - x * x);
		double f = R - d + x * Math.tan(v);
		
//		double f0 = n1*R/(n2-n1);
//		double xx = f0 - R + Math.sqrt(Math.pow(R, 2) - Math.pow(x, 2));
//		double f = Math.sqrt(Math.pow(xx, 2) + Math.pow(x, 2));
		points.add(new Point(x*100, f*100));
	}

	@Override
	public void finish() {
		Plot p1 = new Plot(points);
		p1.setColor(Color.blue);
		getPlots().add(p1);
	}
}