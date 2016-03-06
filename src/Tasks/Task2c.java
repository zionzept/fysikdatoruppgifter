package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.Plot;
import diagram.Point;

public class Task2c extends Task{
	LinkedList<Point> points1;
//	double f0; //avstï¿½nd till bildbrï¿½nnvidd från origo
	double n1 = 1; //brytningsindex luft
	double R = 0.15; //Krï¿½kningsradie, m
	double h = 0.05; //höjd, m
//	double squaredN; // brytningsindex BK7 glas, upphöjt med 2
	
	double a1 = 2.271176;
	double a2 = -9.700709E-3;
	double a3 = 0.0110971;
	double a4 = 4.622809E-5;
	double a5 = 1.616105E-5;
	double a6 = -8.285043E-7;
	
	public Task2c(double from, double to, int points) {
		super(from, to, points, new Diagram("2c", "Våglängd λ/µm", "Avstånd f/cm", 2, 3));
		points1 = new LinkedList<>();
	}

	@Override
	public void compute(double x) {//vï¿½glï¿½ngd
		double squaredN = a1 + a2 * Math.pow(x, 2)
				+ a3 * Math.pow(x, -2) + a4 * Math.pow(x, -4)
				+ a5 * Math.pow(x, -6) + a6 * Math.pow(x, -8);
		double f0 = n1*R/(Math.sqrt(squaredN)-n1);
		double xx = f0 - R + Math.sqrt(Math.pow(R, 2) - Math.pow(h, 2));
		double f = Math.sqrt(Math.pow(xx, 2) + Math.pow(h, 2));
		points1.add(new Point(x, f*100));
	}

	@Override
	public void finish() {
		Plot p1 = new Plot(points1);
		p1.setColor(Color.blue);
		getPlots().add(p1);
	}
}
