package diagram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;

public class Diagram {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	private static final int SPACING = 68;
	private static final int END_SPACING = 30;
	private static final int MARK_LENGTH = 4;
	private static final int TEXT_SPACING = 4;
	private static final int X_CAPTION_SPACING = 30;
	private static final int Y_CAPTION_SPACING = 44;
	
	private String title;
	private String xCaption;
	private String yCaption;
	private int xSignificantDigits;
	private int ySignificantDigits;
	
	private LinkedList<Plot> plots;
	private Bounds bounds;
	
	private BufferedImage image;
	private boolean imageExpired;
	
	public Diagram(String title, String xCaption, String yCaption, int xSignificantDigits, int ySignificantDigits) {
		this.plots = new LinkedList<>();
		this.title = title;
		this.xCaption = xCaption;
		this.yCaption = yCaption;
		this.xSignificantDigits = xSignificantDigits;
		this.ySignificantDigits = ySignificantDigits;
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
	
	public BufferedImage getImage() {
		if (imageExpired) {
			generateImage();
		}
		return image;
	}
	
	public BufferedImage generateImage() {
		image = new BufferedImage(WIDTH + 2 * SPACING, HEIGHT + 2 * SPACING, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = (Graphics2D)image.getGraphics();
	    RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g.setRenderingHints(rh);
		g.fillRect(0, 0, WIDTH + SPACING + END_SPACING, HEIGHT + SPACING + END_SPACING);
		g.setColor(Color.BLACK);
		
		g.drawLine(SPACING - 1, END_SPACING - 1, SPACING - 1, END_SPACING + HEIGHT + 1);
		g.drawLine(SPACING + WIDTH + 1, END_SPACING - 1, SPACING + WIDTH + 1, END_SPACING + HEIGHT + 1);
		g.drawLine(SPACING - 1, END_SPACING - 1, SPACING + WIDTH + 1, END_SPACING - 1);
		g.drawLine(SPACING - 1, END_SPACING + HEIGHT + 1, SPACING + WIDTH + 1, END_SPACING + HEIGHT + 1);
		
		LinkedList<Double> xMarkings = quantify(bounds.getLeft(), bounds.getRight(), 9);
		LinkedList<Double> yMarkings = quantify(bounds.getTop(), bounds.getBottom(), 6);
		for (Double xd : xMarkings) {
			int x = convertX(xd);
			g.drawLine(SPACING + x, HEIGHT + END_SPACING, SPACING + x, HEIGHT + END_SPACING - MARK_LENGTH);
			BigDecimal d = new BigDecimal(xd).round(new MathContext(xSignificantDigits));
			int xOffset = -g.getFontMetrics().stringWidth(d.toString()) / 2;
			int yOffset = (int)Math.round(g.getFontMetrics().getHeight() / 3d * 2d);
			g.drawString(d.toString(), SPACING + x + xOffset, HEIGHT + END_SPACING + TEXT_SPACING + yOffset);
		}
		for (Double yd : yMarkings) {
			int y = convertY(yd);
			g.drawLine(SPACING, END_SPACING + y, SPACING + MARK_LENGTH, END_SPACING + y);
			BigDecimal d = new BigDecimal(yd).round(new MathContext(ySignificantDigits));
			int xOffset = -g.getFontMetrics().stringWidth(d.toString());
			int yOffset = (int)Math.round(g.getFontMetrics().getHeight() / 3d);
			g.drawString(d.toString(), SPACING - TEXT_SPACING + xOffset, END_SPACING + y + yOffset);
		}
		int captionHeight = g.getFontMetrics().getHeight();
		int xCaptionWidth = g.getFontMetrics().stringWidth(xCaption);
		g.drawString(xCaption, SPACING + WIDTH / 2 - xCaptionWidth / 2, END_SPACING + HEIGHT + X_CAPTION_SPACING + captionHeight / 3 * 2);
		
		int yCaptionWidth = g.getFontMetrics().stringWidth(yCaption);  
	    AffineTransform affineTransform = new AffineTransform();
	    affineTransform.rotate(Math.toRadians(-90), 0, 0);
	    Font font = g.getFont();
	    Font rotatedFont = font.deriveFont(affineTransform);
	    g.setFont(rotatedFont);
	    g.drawString(yCaption,SPACING - Y_CAPTION_SPACING, END_SPACING + HEIGHT / 2 + yCaptionWidth / 2);
	    g.setFont(font);
		
		for (Plot plot : plots) {
			g.setColor(plot.getColor());
			LinkedList<Point> points = plot.getPoints();
			boolean first = true;
			int prevX = 0;
			int prevY = 0;
			for (Point point : points) {
				int x = convertX(point.getX());
				int y = convertY(point.getY());
				if (first) {
					g.drawLine(SPACING + x, END_SPACING + y, SPACING + x, END_SPACING + y);
					first = false;
				} else {
					g.drawLine(SPACING + prevX, END_SPACING + prevY, SPACING + x, END_SPACING + y);
				}
				prevX = x;
				prevY = y;
			}
		}
		imageExpired = false;
		g.dispose();
		return image;
	}
	
	private int convertX(double x) {
		return (int)(WIDTH * (x - bounds.getLeft()) / (bounds.getRight() - bounds.getLeft()));
	}
	
	private int convertY(double y) {
		return (int)(HEIGHT - HEIGHT * (y - bounds.getTop()) / (bounds.getBottom() - bounds.getTop()));
	}
	
	private LinkedList<Double> quantify(double low, double high, double preferredSteps) {
		double estStep = (high - low) / preferredSteps;
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
	
	@Override
	public String toString() {
		return title;
	}
}

//BufferedImage txtImage = new BufferedImage(100, 12, BufferedImage.TYPE_4BYTE_ABGR);
//txtImage.getGraphics().drawString(d.toString(), 2, 6);
//AffineTransform at = new AffineTransform();
//at.rotate(Math.PI / 2, 1, 1);
//AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
//txtImage = op.filter(txtImage, null);
//g.drawImage(txtImage, SPACING - TEXT_SPACING, SPACING + y, null);