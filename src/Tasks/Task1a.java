package Tasks;

import java.awt.Color;
import java.util.LinkedList;

import diagram.Diagram;
import diagram.Plot;
import diagram.Point;

public class Task1a extends Task{
	private LinkedList<Point> points1;
	private LinkedList<Point> points2;
	
	private final double n1 = 1.0;	//brytningsindex luft
	private final double n2 = 1.75;	//brytningsindex flintglas	
	
//	double x;	
//	double a2;	//brytningsvinkel a2
//	double Rs;	//Reflektans s-polariserat
//	double Rp;	//Reflektans p-polariserat
	public Task1a(double from, double to, int points){
		super(from, to, points, new Diagram("1a", "infallsvinkel a1/�", "Reflektans", 2, 1));
		this.points1 = new LinkedList<Point>();	
		this.points2 = new LinkedList<Point>();
	}

	@Override
	public void compute(double x) {//infallsvinkel x
		double r1 = Math.toRadians(x);
		double r2 = Math.asin(n1/n2*Math.sin(r1));	//brytningsvinkel a2 i radianer
		
		/*Rs*/
		double Rs = Math.pow(Math.sin(r1 - r2) / Math.sin(r1 + r2), 2); //Reflektans s-polariserat
		points1.add(new Point(x, Rs));
		
		/*Rp*/
		double Rp = Math.pow(Math.tan(r1 - r2) / Math.tan(r1 + r2), 2); //Reflektans p-polariserat
		points2.add(new Point(x, Rp));
	}
	
	@Override
	public void finish() {
		Plot p1 = new Plot(points1);
		p1.setColor(Color.red);
		getPlots().add(p1);
		
		Plot p2 = new Plot(points2);
		p2.setColor(Color.blue);
		getPlots().add(p2);
	}
}
