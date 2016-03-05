package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.DiagramDisplay;
import diagram.Plot;
import diagram.Point;

public class Task1a extends Task{
	
	public Task1a(double from, double to, double scaling){
		super(from, to, scaling, new Diagram("infallsvinkel a1/°", "Reflektans"));
		/*x-axis bounds and scaling*/
		
		double n1 = 1.0;	//brytningsindex luft
		double n2 = 1.75;	//brytningsindex flintglas	
		double a1;	//infallsvinkel a1
		double a2;	//infallsvinkel a2
		double Rs;	//Reflektans s-polariserat
		double Rp;	//Reflektans p-polariserat
		
		/*Rs*/
		LinkedList<Point> points1 = new LinkedList<Point>();
		a1= from;
		while(a1 <= to){
			double r1 = Math.toRadians(a1);
			double r2 = Math.asin(n1/n2*Math.sin(r1));	//infallsvinkel a2
			Rs = Math.pow(Math.sin(r1 - r2) / Math.sin(r1 + r2), 2); //Reflektans s-polariserat
			points1.add(new Point(a1, Rs));
			a1 += scaling;
		}
		Plot p1 = new Plot(points1);
		p1.setColor(Color.red);
		getPlots().add(p1);
		
		/*Rp*/
		LinkedList<Point> points2 = new LinkedList<Point>();
		a1= from;
		while(a1 <= to){
			double r1 = Math.toRadians(a1);
			double r2 = Math.asin(n1/n2*Math.sin(r1));	//infallsvinkel a2
			Rp = Math.pow(Math.tan(r1 - r2) / Math.tan(r1 + r2), 2); //Reflektans p-polariserat
			points2.add(new Point(a1, Rp));
			a1 += scaling;
		}	
		Plot p2 = new Plot(points2);
		p2.setColor(Color.blue);
		getPlots().add(p2);
	}
}
