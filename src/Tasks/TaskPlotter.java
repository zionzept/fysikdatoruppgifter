package Tasks;

import java.util.LinkedList;

import diagram.Diagram;
import diagram.DiagramDisplay;
import diagram.Plot;

public class TaskPlotter {
	
	public TaskPlotter(){
		int resolution = 100;
		LinkedList<Task> tasks = new LinkedList<Task>();
		
		Task t1a = new Task1a(1,90,resolution);
		tasks.add(t1a);
		
		Task t1b = new Task1b(1,40,resolution);
		tasks.add(t1b);
		
		Task t2a = new Task2a(0.0000001,0.05,resolution);
		tasks.add(t2a);
		
		Task t2b = new Task2b(400 *Math.pow(10,-3),  700 *Math.pow(10,-3), resolution);
		tasks.add(t2b);
		
		Task t2c = new Task2c(400 *Math.pow(10,-3),  700 *Math.pow(10,-3), resolution);
		tasks.add(t2c);
		
		for(Task t: tasks){
			t.compute();
			LinkedList<Plot> plots = t.getPlots();
			Diagram d = t.getDiagram();
			for(Plot p: plots){
				d.addPlot(p);
			}
			DiagramDisplay disp = new DiagramDisplay(d);
			disp.show();
		}
	}
	
	public static void main(String[] args){
		new TaskPlotter();
	}
}
