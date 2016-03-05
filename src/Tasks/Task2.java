package Tasks;

import java.util.LinkedList;

import diagram.Diagram;
import diagram.DiagramDisplay;
import diagram.Plot;
import diagram.Point;

public class Task2 {
	public static void main(String[] args){
		/*x-axis bounds and scaling*/
		double from = 400 *Math.pow(10,-9);	//v�gl�ngd, m
		double to = 700 *Math.pow(10,-9);	//v�gl�ngd, m
		double scaling = 10 *Math.pow(10,-9);
		
		
		double lambda; //v�gl�ngd
		double f; //avst�nd till bildbr�nnvidd.
		double n1 = 1; //brytningsindex luft
		double n2 = 1.5; //brytningsindex glas;
		double R = 0.15; //Kr�kningsradie, m
		double D = 0.1; //Diameter i meter
		
		
	/////////////////////////////////Uppgift 2a/////////////////////////////////
		Diagram d1 = new Diagram(" ", "");
		LinkedList<Point> points1 = new LinkedList<>();
		lambda = from;
		while(lambda <= to){
			
			lambda += scaling;
		}
		
		Plot p1 = new Plot(points1);
		d1.addPlot(p1);
		DiagramDisplay disp1 = new DiagramDisplay(d1);
		disp1.show();
		
	/////////////////////////////////Uppgift 2b/////////////////////////////////
		Diagram d2 = new Diagram(" ", "");
		LinkedList<Point> points2 = new LinkedList<>();
		double squaredN; //brytningsindex BK7 glas, upph�jt med 2
		lambda = from;
		while(lambda  <= to){
			
		squaredN = 2.271176 - 9.700709*Math.pow(10, 3)*lambda*lambda + 0.0110971*Math.pow(lambda, -2) + 4.622809*Math.pow(10, -5)*Math.pow(lambda, -4) + 1.616105*Math.pow(10,-5)*Math.pow(lambda, -6) - 8.285043*Math.pow(10,-7)*Math.pow(lambda, -8);
		lambda += scaling;
		}
		
		Plot p2 = new Plot(points2);
		d2.addPlot(p2);
		DiagramDisplay disp2 = new DiagramDisplay(d2);
		disp2.show();
		
	/////////////////////////////////Uppgift 2c/////////////////////////////////	
		
	}
}