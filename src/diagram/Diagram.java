package diagram;

import java.awt.Image;
import java.util.LinkedList;

public class Diagram {

	private static String xcaption;
	private static String ycaption;
	
	private static LinkedList<Plot> plots;
	
	public Diagram(String xcaption, String ycaption) {
		this.xcaption = xcaption;
		this.ycaption = ycaption;
	}
	
	public void addPlot(Plot plot) {
		plots.add(plot);
	}
	
	public Image createImage() {
		return null;
	}
}