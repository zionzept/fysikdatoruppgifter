package Tasks;

import java.util.LinkedList;

import diagram.Diagram;
import diagram.DiagramDisplay;
import diagram.Plot;
import diagram.Point;

public class Task2a extends Task{
	
	public Task2a(double from, double to, double scaling) {
		super(from, to, scaling, new Diagram("Våglängd /m", "NÅT ANNAT"));
		/*x-axis bounds and scaling*/
		from *= Math.pow(10, -2); // höjd
		to *= Math.pow(10, -2); // höjd
		scaling *= Math.pow(10, -2);
		
		double h; //höjd
		double f0; //avstånd till bildbrännvidd från origo
		double n1 = 1; //brytningsindex luft
		double n2 = 1.5; //brytningsindex glas;
		double R = 0.15; //Krï¿½kningsradie, m
		double D = 0.1; //Diameter i meter

		LinkedList<Point> points1 = new LinkedList<>();
		h = from;
		while(h <= to){
			f0 = n1*R/(n2-n1);
			double f = 1;
			points1.add(new Point(h, f));
			h += scaling;
		}
		

	
	}
}