package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.Plot;
import diagram.Point;

public class Task2b extends Task {
	private LinkedList<Point> points;
	
	private final double a1 = 2.271176;
	private final double a2 = -9.700709E-3;
	private final double a3 = 0.0110971;
	private final double a4 = 4.622809E-5;
	private final double a5 = 1.616105E-5;
	private final double a6 = -8.285043E-7;
	
//	double squaredN; // brytningsindex BK7 glas, upphï¿½jt med 2
	public Task2b(double from, double to, int points) {
		super(from, to, points, new Diagram("2b", "Våglängd λ/µm", "brytningsindex n", 2, 4));
		this.points = new LinkedList<>();	
	}

	@Override
	public void compute(double x) {// vï¿½glï¿½ngd
		double n = Math.sqrt(a1 + a2 * Math.pow(x, 2)
				+ a3 * Math.pow(x, -2) + a4 * Math.pow(x, -4)
				+ a5 * Math.pow(x, -6) + a6 * Math.pow(x, -8));
		points.add(new Point(x, n));
	}

	@Override
	public void finish() {
		Plot p1 = new Plot(points);
		p1.setColor(Color.blue);
		getPlots().add(p1);
	}
}