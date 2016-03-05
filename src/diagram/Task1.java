package diagram;

import java.util.LinkedList;

public class Task1 {
	
	public static void main(String args[]){
		double n1 = 1.0;	//brytningsindex luft
		double n2 = 1.75;	//brytningsindex flintglas
		double a1 = 0;			//infallsvinkel a1
		double a2 = Math.asin(n1/n2*Math.sin(a1));	//infallsvinkel a2
		double Rs = Math.pow(Math.sin(a1 - a2), Math.sin(a1 + a2)); //Reflektans s-polariserat
		double Rp = Math.pow(Math.tan(a1 - a2), Math.tan(a1 + a2)); //Reflektans p-polariserat
		
		/*x-axis bounds and scaling*/
		double from = 0;
		double to = 10;
		double scaling = 1;
		
		
		Diagram d1 = new Diagram("infallsvinkel a1/°", "Reflektans, s-polarisation");
		Diagram d2 = new Diagram("infallsvinkel a1/°", "Reflektans, p-polarisation");
		
		LinkedList<Point> points1 = new LinkedList<Point>();
		double i= from;
		while(i <= to){
			//points1.add(new Point());
			i += scaling;
		}
		
		LinkedList<Point> points2 = new LinkedList<Point>();
		i= from;
		while(i <= to){
			//points2.add(new Point());
			i += scaling;
		}
		Plot p1 = new Plot(points1);
		Plot p2 = new Plot(points2);
		
		d1.addPlot(p1);
		d2.addPlot(p2);
	
		DiagramDisplay disp1 = new DiagramDisplay(d1);
		DiagramDisplay disp2 = new DiagramDisplay(d2);
		disp1.show();
		disp2.show();
	}
}
