package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.DiagramDisplay;
import diagram.Plot;
import diagram.Point;

public class Task1b extends Task {
	public Task1b(double from, double to, int points) {
		super(from, to, points, new Diagram("1b", "infallsvinkel a2/�", "Reflektans", 2, 2));
		/* x-axis bounds and scaling */

		double n1 = 1.0; // brytningsindex luft
		double n2 = 1.75; // brytningsindex flintglas
		double a1; // brytningsvinkel a1
		double a2; // infallsvinkel a2
		double Rs; // Reflektans s-polariserat
		double Rp; // Reflektans p-polariserat

		/* Rs */
		LinkedList<Point> points1 = new LinkedList<Point>();
		a2 = from;
		while (a2 <= to) {
			double r2 = Math.toRadians(a2);
			double r1 = Math.asin(n2 / n1 * Math.sin(r2)); // brytningsvinkel a1 i radianer
			Rs = Math.pow(Math.sin(r2 - r1) / Math.sin(r2 + r1), 2); // Reflektans s-polariserat
			points1.add(new Point(a2, Rs));
			a2 += scaling;
		}
		Plot p1 = new Plot(points1);
		p1.setColor(Color.red);
		getPlots().add(p1);
		
		/* Rp */
		LinkedList<Point> points2 = new LinkedList<Point>();
		a2 = from;
		while (a2 <= to) {
			double r2 = Math.toRadians(a2);
			double r1 = Math.asin(n2 / n1 * Math.sin(r2)); // brytningsvinkel a1 i radianer
			Rp = Math.pow(Math.tan(r2 - r1) / Math.tan(r2 + r1), 2); // Reflektans p-polariserat
			points2.add(new Point(a2, Rp));
			a2 += scaling;
		}
		Plot p2 = new Plot(points2);
		p2.setColor(Color.blue);
		getPlots().add(p2);
	}
}
