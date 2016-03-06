package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.Plot;
import diagram.Point;

public class Task2b extends Task {
	LinkedList<Point> points1;
//	double squaredN; // brytningsindex BK7 glas, upphï¿½jt med 2
	double a1 = 2.271176;
	double a2 = -9.700709E-3;
	double a3 = 0.0110971;
	double a4 = 4.622809E-5;
	double a5 = 1.616105E-5;
	double a6 = -8.285043E-7;
	
	public Task2b(double from, double to, int points) {
		super(from, to, points, new Diagram("2b", "Våglängd λ/µm", "brytningsindex n", 2, 4));
		points1 = new LinkedList<>();	
	}

	@Override
	public void compute(double x) {// vï¿½glï¿½ngd
		double squaredN = a1 + a2 * Math.pow(x, 2)
				+ a3 * Math.pow(x, -2) + a4 * Math.pow(x, -4)
				+ a5 * Math.pow(x, -6) + a6 * Math.pow(x, -8);
		points1.add(new Point(x, Math.sqrt(squaredN)));
	}

	@Override
	public void finish() {
		Plot p1 = new Plot(points1);
		p1.setColor(Color.blue);
		getPlots().add(p1);
	}
}