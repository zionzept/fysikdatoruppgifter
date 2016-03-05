package Tasks;

import java.util.LinkedList;

import diagram.Diagram;
import diagram.DiagramDisplay;
import diagram.Plot;
import diagram.Point;

public class TaskPlotter {
	
	private LinkedList<Point> pl;
	
	public TaskPlotter(){
		LinkedList<Task> tasks = new LinkedList<Task>();
	
		Task t1a = new Task1a(1,10,-1);
		tasks.add(t1a);

		
		for(Task t: tasks){
			LinkedList<Plot> plots = t.getPlots();
			Diagram d = t.getDiagram();
			for(Plot p: plots){
			d.addPlot(p);
			DiagramDisplay disp = new DiagramDisplay(d);
			disp.show();	
			}
		}
	}
	
	public static void main(String[] args){
		new TaskPlotter();
	}
}
