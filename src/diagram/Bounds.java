package diagram;

public class Bounds {
	private final double left;
	private final double right;
	private final double top;
	private final double bottom;
	
	public Bounds(double left, double right, double top, double bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}
	
	public double getLeft() {
		return left;
	}
	
	public double getRight() {
		return right;
	}
	
	public double getTop() {
		return top;
	}
	
	public double getBottom() {
		return bottom;
	}
	
	public Bounds merge(Bounds bounds) {
		double x0 = Math.min(left, bounds.left);
		double x1 = Math.max(right, bounds.right);
		double y0 = Math.min(top, bounds.top);
		double y1 = Math.max(bottom, bounds.bottom);
		return new Bounds(x0, x1, y0, y1);
	}
}