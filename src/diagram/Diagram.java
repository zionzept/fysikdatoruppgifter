package diagram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;

public class Diagram {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	private static final int SPACING = 40;
	private static final int MARK_LENGTH = 4;
	private static final int TEXT_SPACING = 4;
	
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
		
		LinkedList<Double> xMarkings = quantify(bounds.getLeft(), bounds.getRight(), 9);
		LinkedList<Double> yMarkings = quantify(bounds.getTop(), bounds.getBottom(), 9);
		for (Double xd : xMarkings) {
			int x = convertX(xd);
			g.drawLine(SPACING + x, HEIGHT + SPACING, SPACING + x, HEIGHT + SPACING - MARK_LENGTH);
			BigDecimal d = new BigDecimal(xd).round(new MathContext(3));
			int xOffset = -g.getFontMetrics().stringWidth(d.toString()) / 2;
			int yOffset = (int)Math.round(g.getFontMetrics().getHeight() / 3d * 2d);
			g.drawString(d.toString(), SPACING + x + xOffset, HEIGHT + SPACING + TEXT_SPACING + yOffset);
		}
		for (Double yd : yMarkings) {
			int y = convertY(yd);
			g.drawLine(SPACING, SPACING + y, SPACING + MARK_LENGTH, SPACING + y);
			BigDecimal d = new BigDecimal(yd).round(new MathContext(3));
			int xOffset = -g.getFontMetrics().stringWidth(d.toString());
			int yOffset = (int)Math.round(g.getFontMetrics().getHeight() / 3d);
			g.drawString(d.toString(), SPACING - TEXT_SPACING + xOffset, SPACING + y + yOffset);
		}
		
		System.out.println("Bounds " + bounds);
		System.out.println("plots: " + plots.size());
		for (Plot plot : plots) {
			g.setColor(plot.getColor());
			LinkedList<Point> points = plot.getPoints();
			boolean first = true;
			int prevX = 0;
			int prevY = 0;
			System.out.println("  Points: " + points.size());
			for (Point point : points) {
				System.out.println("   " + point.getX() + " " + point.getY());
				int x = convertX(point.getX());
				int y = convertY(point.getY());
				System.out.println("  convert: " + point.getX() + "," + point.getY() + "  ->  " + x + ", " + y);
				if (first) {
					g.drawLine(SPACING + x, SPACING + y, SPACING + x, SPACING + y);
					first = false;
				} else {
					g.drawLine(SPACING + prevX, SPACING + prevY, SPACING + x, SPACING + y);
				}
				prevX = x;
				prevY = y;
			}
		}
		
		imageExpired = false;
		return image;
	}
	
	private int convertX(double x) {
		System.out.println("x: " + x + " : " + (x - bounds.getLeft()) + "  /  " + (bounds.getRight() - bounds.getLeft()));
		return (int)(WIDTH * (x - bounds.getLeft()) / (bounds.getRight() - bounds.getLeft()));
	}
	
	private int convertY(double y) {
		System.out.println("y: " + y + " : " + (y - bounds.getTop()) + "  /  " + (bounds.getBottom() - bounds.getTop()));
		return (int)(HEIGHT - HEIGHT * (y - bounds.getTop()) / (bounds.getBottom() - bounds.getTop()));
	}
	
	private LinkedList<Double> quantify(double low, double high, double preferredValues) {
		double estStep = (high - low) / preferredValues;
		double step = 1;
		double stepUnder = 0;
		double stepOver = 0;
		if (estStep < 1) {
			while (estStep < step) {
				stepOver = step;
				step /= 2d;
				stepUnder = step;
				if (estStep < step) {
					stepOver = step;
					step /= 2.5d;
					stepUnder = step;
					if (estStep < step) {
						stepOver = step;
						step /= 2d;
						stepUnder = step;
					}
				}
			}
		} else {
			while (estStep >= step) {
				stepUnder = step;
				step *= 2d;
				stepOver = step;
				if (estStep >= step) {
					stepUnder = step;
					step *= 2.5d;
					stepOver = step;
					if (estStep >= step) {
						stepUnder = step;
						step *= 2d;
						stepOver = step;
					}
				}
			}
		}
		if (stepOver - step < step - stepUnder) {
			step = stepOver;
		} else {
			step = stepUnder;
		}
		LinkedList<Double> values = new LinkedList<>();
		double value = Math.floor(high / step) * step;
		while (value >= low) {
			values.add(value);
			value -= step;
		}
		return values;
	}
}

//BufferedImage txtImage = new BufferedImage(100, 12, BufferedImage.TYPE_4BYTE_ABGR);
//txtImage.getGraphics().drawString(d.toString(), 2, 6);
//AffineTransform at = new AffineTransform();
//at.rotate(Math.PI / 2, 1, 1);
//AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
//txtImage = op.filter(txtImage, null);
//g.drawImage(txtImage, SPACING - TEXT_SPACING, SPACING + y, null);