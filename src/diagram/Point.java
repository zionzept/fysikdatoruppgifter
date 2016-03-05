package diagram;

public class Point implements Comparable<Point> {
	private final double x;
	private final double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public int compareTo(Point o) {
		Double x0 = new Double(x);
		Double x1 = new Double(o.x);
		return x0.compareTo(x1);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}