package diagram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Diagram {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	private static final int SPACING = 40;
	
	private String xcaption;
	private String ycaption;
	
	private LinkedList<Plot> plots;
	private Bounds bounds;
	
	private BufferedImage image;
	private boolean imageExpired;
	
	public Diagram(String xcaption, String ycaption) {
		this.plots = new LinkedList<>();
		this.xcaption = xcaption;
		this.ycaption = ycaption;
		this.bounds = Bounds.DEFAULT;
		imageExpired = true;
	}
	
	public void addPlot(Plot plot) {
		if (plots.isEmpty()) {
			bounds = plot.getBounds();
		} else {
			bounds = bounds.merge(plot.getBounds());
		}
		plots.add(plot);
		imageExpired = true;
	}
	
	public Image getImage() {
		if (imageExpired) {
			generateImage();
		}
		return image;
	}
	
	public Image generateImage() {
		image = new BufferedImage(WIDTH + 2 * SPACING, HEIGHT + 2 * SPACING, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = image.getGraphics();
		g.fillRect(0, 0, WIDTH + 2 * SPACING, HEIGHT + 2 * SPACING);
		
		g.setColor(Color.BLACK);
		
		g.drawLine(SPACING, SPACING, SPACING, SPACING + HEIGHT);
		g.drawLine(SPACING + WIDTH, SPACING, SPACING + WIDTH, SPACING + HEIGHT);
		g.drawLine(SPACING, SPACING, SPACING + WIDTH, SPACING);
		g.drawLine(SPACING, SPACING + HEIGHT, SPACING + WIDTH, SPACING + HEIGHT);
		System.out.println("plots: " + plots.size());
		for (Plot plot : plots) {
			g.setColor(plot.getColor());
			LinkedList<Point> points = plot.getPoints();
			boolean first = true;
			int prevX = 0;
			int prevY = 0;
			System.out.println("  Points: " + points.size());
			for (Point point : points) {
				if (first) {
					prevX = convertX(point.getX());
					prevY = convertY(point.getY());
					g.drawLine(prevX, prevY, prevX, prevY);
				} else {
					int x = convertX(point.getX());
					int y = convertY(point.getY());
					g.drawLine(prevX, prevY, x, y);
					prevX = x;
					prevY = y;
				}
			}
		}
		
		imageExpired = false;
		return image;
	}
	
	private int convertX(double x) {
		return (int)(WIDTH * x / (bounds.getRight() - bounds.getLeft()));
	}
	
	private int convertY(double y) {
		return (int)(HEIGHT * y / (bounds.getBottom() - bounds.getTop()));
	}
}