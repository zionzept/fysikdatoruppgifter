package diagram;

public class Bounds {
	public static final Bounds DEFAULT = new Bounds(0, 0, 10, 10);
	public static final Bounds ZERO = new Bounds(0, 0, 0, 0);
	private final double left;
	private final double top;
	private final double right;
	private final double bottom;
	
	public Bounds(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	public double getLeft() {
		return left;
	}

	public double getTop() {
		return top;
	}
	
	public double getRight() {
		return right;
	}	
	
	public double getBottom() {
		return bottom;
	}
	
	public Bounds merge(Bounds bounds) {
		double x0 = Math.min(left, bounds.left);
		double y0 = Math.min(top, bounds.top);
		double x1 = Math.max(right, bounds.right);
		double y1 = Math.max(bottom, bounds.bottom);
		return new Bounds(x0, y0, x1, y1);
	}
	
	@Override
	public String toString() {
		return "[(" + left + ", " + top + "), (" + right + ", " + bottom + ")]";
	}
}