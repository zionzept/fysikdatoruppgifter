package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.Plot;
import diagram.Point;

public class Task1b extends Task {
	private LinkedList<Point> points1;
	private LinkedList<Point> points2;
	
	private final double n1 = 1.0; // brytningsindex luft
	private final double n2 = 1.75; // brytningsindex flintglas
	
	public Task1b(double from, double to, int points) {
		super(from, to, points, new Diagram("1b", "Infallsvinkel a2/°", "Reflektans", 2, 1));
		this.points1 = new LinkedList<Point>();
		this.points2 = new LinkedList<Point>();
	}

	@Override
	public void compute(double x) {						//infallsvinkel x i grader
		double r2 = Math.toRadians(x);					//infallsvinkel till radianer
		double r1 = Math.asin(n2 / n1 * Math.sin(r2));	// brytningsvinkel a1 i radianer
	
		double Rs = Math.pow(Math.sin(r2 - r1) / Math.sin(r2 + r1), 2); // Reflektans s-polariserat
		points1.add(new Point(x, Rs));

		double Rp = Math.pow(Math.tan(r2 - r1) / Math.tan(r2 + r1), 2); // Reflektans p-polariserat
		points2.add(new Point(x, Rp));
	}

	@Override
	public void finish() {
		Plot p1 = new Plot(points1);
		p1.setColor(Color.red);
		getPlots().add(p1);
		
		Plot p2 = new Plot(points2);
		p2.setColor(Color.blue);
		getPlots().add(p2);
	}
}
