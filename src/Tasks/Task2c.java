package Tasks;

import diagram.Diagram;

public class Task2c extends Task{
	public Task2c(double from, double to, double scaling) {
		super(from, to, scaling, new Diagram("Våglängd /λ", "NÅT ANNAT"));
		/*x-axis bounds and scaling*/
		from *= Math.pow(10,-9);	//våglängd, m
		to *= Math.pow(10,-9);	//våglängd, m
		scaling *= Math.pow(10,-9);
	
		double lambda; //vï¿½glï¿½ngd
		double f; //avstï¿½nd till bildbrï¿½nnvidd.
		double n1 = 1; //brytningsindex luft
		double n2 = 1.5; //brytningsindex glas;
		double R = 0.15; //Krï¿½kningsradie, m
		double D = 0.1; //Diameter i meter
	}
}
