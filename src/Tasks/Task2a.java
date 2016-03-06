package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.DiagramDisplay;
import diagram.Plot;
import diagram.Point;

public class Task2a extends Task{
	
	public Task2a(double from, double to, double scaling) {
		super(from, to, scaling, new Diagram("höjd h/cm", "avstånd f/cm"));
		
		double h; //höjd, m
		double f0; //avstånd till bildbrännvidd från origo
		double n1 = 1; //brytningsindex luft
		double n2 = 1.5; //brytningsindex glas;
		double R = 0.15; //Krï¿½kningsradie, m
		double D = 0.1; //Diameter, m

		LinkedList<Point> points1 = new LinkedList<>();
		h = from;
		while(h <= D){
			f0 = n1*R/(n2-n1);
			double x = f0 - R + Math.sqrt(Math.pow(R, 2) - Math.pow(h, 2));
			double f = Math.sqrt(Math.pow(x, 2) + Math.pow(h, 2));
			points1.add(new Point(h*100, f*100));
			h += scaling;
		}
		Plot p1 = new Plot(points1);
		p1.setColor(Color.blue);
		getPlots().add(p1);
	}
}