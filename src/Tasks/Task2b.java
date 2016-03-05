package Tasks;

import java.util.LinkedList;

import diagram.Diagram;
import diagram.Point;

public class Task2b extends Task {
	public Task2b(double from, double to, double scaling) {
		super(from, to, scaling, new Diagram("Våglängd /λ", "NÅT ANNAT"));
		/* x-axis bounds and scaling */
		from *= Math.pow(10, -9); // våglängd, m
		to *= Math.pow(10, -9); // våglängd, m
		scaling *= Math.pow(10, -9);

		double lambda; // vï¿½glï¿½ngd
		double f; // avstï¿½nd till bildbrï¿½nnvidd.
		double n1 = 1; // brytningsindex luft
		double n2 = 1.5; // brytningsindex glas;
		double R = 0.15; // Krï¿½kningsradie, m
		double D = 0.1; // Diameter i meter

		LinkedList<Point> points2 = new LinkedList<>();
		double squaredN; // brytningsindex BK7 glas, upphï¿½jt med 2

		lambda = from;
		while (lambda <= to) {

			squaredN = 2.271176 - 9.700709 * Math.pow(10, 3) * lambda * lambda + 0.0110971 * Math.pow(lambda, -2)
					+ 4.622809 * Math.pow(10, -5) * Math.pow(lambda, -4)
					+ 1.616105 * Math.pow(10, -5) * Math.pow(lambda, -6)
					- 8.285043 * Math.pow(10, -7) * Math.pow(lambda, -8);
			lambda += scaling;
		}
	}
}
