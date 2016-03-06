package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.Plot;
import diagram.Point;

public class Task2b extends Task {
	public Task2b(double from, double to, double scaling) {
		super(from, to, scaling, new Diagram("Våglängd λ/nm", "brytningsindex n"));
		/* x-axis bounds and scaling */

		double lambda; // vï¿½glï¿½ngd
		double squaredN; // brytningsindex BK7 glas, upphï¿½jt med 2
		double D = 0.1; // Diameter i meter

		LinkedList<Point> points1 = new LinkedList<>();

		lambda = from;
		while (lambda <= to) {
			squaredN = 2.271176 - 9.700709 * Math.pow(10, -3) * lambda * lambda + 0.0110971 * Math.pow(lambda, -2)
			+ 4.622809 * Math.pow(10, -5) * Math.pow(lambda, -4)
			+ 1.616105 * Math.pow(10, -5) * Math.pow(lambda, -6)
			- 8.285043 * Math.pow(10, -7) * Math.pow(lambda, -8);
			Point p = new Point(lambda*Math.pow(10, 9), Math.sqrt(squaredN));
			points1.add(p);
			lambda += scaling;
		}
		Plot p1 = new Plot(points1);
		p1.setColor(Color.blue);
		getPlots().add(p1);
	}
}
