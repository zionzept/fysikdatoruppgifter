package diagram;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Plot {
	public List<Point> points;
	
	public Plot() {
		this.points = new LinkedList<>();
	}
	
	public Plot(List<Point> points) {
		this.points = points;
		Collections.sort(points);
	}
	
	public void add(List<Point> points) {
		this.points.addAll(points);
		Collections.sort(points);
	}
	
	public void add(Point point) {
		this.points.add(point);
		Collections.sort(points);
	}
	
	public List<Point> getPoints() {
		return points;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Point p : points) {
			sb.append(p.toString());
		}
		sb.append("]");
		return sb.toString();
	}
}