package diagram;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Diagram {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	
	private String xcaption;
	private String ycaption;
	
	private LinkedList<Plot> plots;
	
	private BufferedImage image;
	private boolean imageExpired;
	
	public Diagram(String xcaption, String ycaption) {
		this.plots = new LinkedList<>();
		this.xcaption = xcaption;
		this.ycaption = ycaption;
		imageExpired = true;
	}
	
	public void addPlot(Plot plot) {
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
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = image.getGraphics();
		g.drawLine(0, 0, WIDTH, HEIGHT);
		imageExpired = false;
		return image;
	}
}