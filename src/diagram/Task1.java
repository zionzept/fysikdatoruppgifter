package diagram;

import java.util.LinkedList;

public class Task1 {
	
	public static void main(String args[]){
		/*x-axis bounds and scaling*/
		double from = 0;
		double to = 10;
		double scaling = 1;
		
		double n1 = 1.0;	//brytningsindex luft
		double n2 = 1.75;	//brytningsindex flintglas		
		double a2;	//infallsvinkel a1
		double Rs;	//Reflektans s-polariserat
		double Rp;	//Reflektans p-polariserat
		
	/////////////////////////////////Uppgift 1a ljus från luft till glas/////////////////////////////////
		Diagram d1 = new Diagram("infallsvinkel a1/°", "Reflektans, s-polarisation");
		Diagram d2 = new Diagram("infallsvinkel a1/°", "Reflektans, p-polarisation");
		
		/*Rs*/
		LinkedList<Point> points1 = new LinkedList<Point>();
		double a1= from;
		while(a1 <= to){
			a2 = Math.asin(n1/n2*Math.sin(a1));	//infallsvinkel a2
			Rs = Math.pow(Math.sin(a1 - a2), Math.sin(a1 + a2)); //Reflektans s-polariserat
			points1.add(new Point(a1, Rs));
			a1 += scaling;
		}
		
		/*Rp*/
		LinkedList<Point> points2 = new LinkedList<Point>();
		a1= from;
		while(a1 <= to){
			a2 = Math.asin(n1/n2*Math.sin(a1));	//infallsvinkel a2
			Rp = Math.pow(Math.tan(a1 - a2), Math.tan(a1 + a2)); //Reflektans p-polariserat
			points1.add(new Point(a1, Rp));
			a1 += scaling;
		}
		
		Plot p1 = new Plot(points1);
		Plot p2 = new Plot(points2);
		
		d1.addPlot(p1);
		d2.addPlot(p2);
	
		DiagramDisplay disp1 = new DiagramDisplay(d1);
		DiagramDisplay disp2 = new DiagramDisplay(d2);
		disp1.show();
		disp2.show();
		
	/////////////////////////////////UPPGIFT 1a END/////////////////////////////////
		
/////////////////////////////////Uppgift 1b ljus från glas till luft/////////////////////////////////
		
		Diagram d3 = new Diagram("infallsvinkel a1/°", "Reflektans, s-polarisation");
		Diagram d4 = new Diagram("infallsvinkel a1/°", "Reflektans, p-polarisation");
		
		/*Rs*/
		LinkedList<Point> points3 = new LinkedList<Point>();
		a1= from;
		while(a1 <= to){
			a2 = Math.asin(n1/n2*Math.sin(a1));	//infallsvinkel a2
			//points2.add(new Point());
			a1 += scaling;
		}
		/*Rp*/
		LinkedList<Point> points4 = new LinkedList<Point>();
		a1= from;
		while(a1 <= to){
			a2 = Math.asin(n1/n2*Math.sin(a1));	//infallsvinkel a2
			//points2.add(new Point());
			a1 += scaling;
		}
		
		Plot p3 = new Plot(points3);
		Plot p4 = new Plot(points4);
		
		d1.addPlot(p3);
		d2.addPlot(p4);
	
		DiagramDisplay disp3 = new DiagramDisplay(d3);
		DiagramDisplay disp4 = new DiagramDisplay(d4);
		disp3.show();
		disp4.show();
		
/////////////////////////////////UPPGIFT 1b END/////////////////////////////////
	}
}
