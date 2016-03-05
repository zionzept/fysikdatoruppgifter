package Tasks;

import java.util.LinkedList;

import diagram.Diagram;
import diagram.DiagramDisplay;
import diagram.Plot;
import diagram.Point;

public class Task1b extends Task {
	public Task1b(double from, double to, double scaling) {
		super(from, to, scaling, new Diagram("", ""));
		/* x-axis bounds and scaling */

		double n1 = 1.0; // brytningsindex luft
		double n2 = 1.75; // brytningsindex flintglas
		double a1; // infallsvinkel a1
		double a2; // infallsvinkel a2
		double Rs; // Reflektans s-polariserat
		double Rp; // Reflektans p-polariserat

		Diagram d3 = new Diagram("infallsvinkel a1/�", "Reflektans, s-polarisation");
		Diagram d4 = new Diagram("infallsvinkel a1/�", "Reflektans, p-polarisation");

		/* Rs */
		LinkedList<Point> points3 = new LinkedList<Point>();
		a2 = from;
		while (a2 <= to) {
			double r2 = Math.toRadians(a2);
			double r1 = Math.asin(n2 / n1 * Math.sin(r2)); // infallsvinkel a1
			Rs = Math.pow(Math.sin(r2 - r1) / Math.sin(r2 + r1), 2); // Reflektans
																		// s-polariserat
			points3.add(new Point(a2, Rs));
			a2 += scaling;
		}
		/* Rp */
		LinkedList<Point> points4 = new LinkedList<Point>();
		a2 = from;
		while (a2 <= to) {
			double r2 = Math.toRadians(a2);
			double r1 = Math.asin(n2 / n1 * Math.sin(r2)); // infallsvinkel a1
			Rp = Math.pow(Math.tan(r2 - r1) / Math.tan(r2 + r1), 2); // Reflektans
																		// p-polariserat
			points4.add(new Point(a2, Rp));
			a2 += scaling;
		}

		Plot p3 = new Plot(points3);
		Plot p4 = new Plot(points4);

		d3.addPlot(p3);
		d4.addPlot(p4);

		DiagramDisplay disp3 = new DiagramDisplay(d3);
		DiagramDisplay disp4 = new DiagramDisplay(d4);
		disp3.show();
		disp4.show();
	}
}
