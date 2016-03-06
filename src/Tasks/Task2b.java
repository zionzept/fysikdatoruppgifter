package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.Plot;
import diagram.Point;

public class Task2b extends Task {
	public Task2b(double from, double to, int points) {
		super(from, to, points, new Diagram("2b", "Våglängd λ/µm", "brytningsindex n", 2, 4));

		double lambda; // vï¿½glï¿½ngd
		double squaredN; // brytningsindex BK7 glas, upphï¿½jt med 2

		LinkedList<Point> points1 = new LinkedList<>();
		
		lambda = from;
		while (lambda <= to) {
			double a1 = 2.271176;
			double a2 = -9.700709E-3;
			double a3 = 0.0110971;
			double a4 = 4.622809E-5;
			double a5 = 1.616105E-5;
			double a6 = -8.285043E-7;
			squaredN = a1 + a2 * Math.pow(lambda, 2)
					+ a3 * Math.pow(lambda, -2) + a4 * Math.pow(lambda, -4)
					+ a5 * Math.pow(lambda, -6) + a6 * Math.pow(lambda, -8);
			points1.add(new Point(lambda, Math.sqrt(squaredN)));
			lambda += scaling;
		}
		Plot p1 = new Plot(points1);
		p1.setColor(Color.blue);
		getPlots().add(p1);
	}
}