package Tasks;

import java.util.LinkedList;

import diagram.Diagram;
import diagram.DiagramDisplay;
import diagram.Plot;

public class TaskPlotter {
	
	public TaskPlotter(){
		LinkedList<Task> tasks = new LinkedList<Task>();
//		Task t1a = new Task1a(1,90,1);
//		tasks.add(t1a);
//		
//		Task t1b = new Task1b(1,90,1);
//		tasks.add(t1b);
		
//		Task t2a = new Task2a(0,0,1*Math.pow(10, -2));
//		tasks.add(t2a);
		
		Task2b t2b = new Task2b(400 *Math.pow(10,-3),  700 *Math.pow(10,-3), 10 *Math.pow(10,-3));
		tasks.add(t2b);
//		
//		Task2c t2c = new Task2c(400 *Math.pow(10,-3),  700 *Math.pow(10,-3), 10 *Math.pow(10,-3));
//		tasks.add(t2c);
		
		for(Task t: tasks){
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
