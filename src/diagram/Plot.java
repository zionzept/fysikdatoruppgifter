package diagram;

import java.util.Collections;
import java.util.LinkedList;

public class Plot {
	public LinkedList<Point> points;
	
	public Plot(LinkedList<Point> points) {
		this.points = points;
		Collections.sort(points);
	}
	
	public LinkedList<Point> getPoints() {
		return new LinkedList<Point>(points);
	}
	
	public double getMinX() {
		return points.get(0).getX();
	}
	
	public double getMaxX() {
		return points.get(points.size() - 1).getX();
	}
	
	public double getMinY() {
		return points.get(0).getX();
	}
	
	public double getMaxY() {
		return points.get(points.size() - 1).getX();
	}
	
	public Bounds getBounds() {
		double x0 = points.get(0).getX();
		double x1 = points.get(points.size() - 1).getX();
		double y0 = Double.MAX_VALUE;
		double y1 = Double.MIN_VALUE;
		for (Point p : points) {
			if (p.getY() < y0) {
				y0 = p.getY();
			}
			if (p.getY() > y1) {
				y1 = p.getY();
			}
		}
		return new Bounds(x0, x1, y0, y1);
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