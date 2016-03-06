package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.Plot;
import diagram.Point;

public class Task2a extends Task{
	LinkedList<Point> points1;
//	double f0; //avstånd till bildbrännvidd från origo
	double n1 = 1; //brytningsindex luft
	double n2 = 1.5; //brytningsindex glas;
	double R = 0.15; //Krï¿½kningsradie, m
	
	public Task2a(double from, double to, int points) {
		super(from, to, points, new Diagram("2a", "höjd h/cm", "avstånd f/cm", 1, 3));
		points1 = new LinkedList<>();
	}

	@Override
	public void compute(double x) {//höjd, m
		double f0 = n1*R/(n2-n1);
		double xx = f0 - R + Math.sqrt(Math.pow(R, 2) - Math.pow(x, 2));
		double f = Math.sqrt(Math.pow(xx, 2) + Math.pow(x, 2));
		points1.add(new Point(x*100, f*100));
	}

	@Override
	public void finish() {
		Plot p1 = new Plot(points1);
		p1.setColor(Color.blue);
		getPlots().add(p1);
	}
}