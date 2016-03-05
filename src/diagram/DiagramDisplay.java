package diagram;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DiagramDisplay {
	private Diagram diagram;
	
	public DiagramDisplay(Diagram diagram) {
		this.diagram = diagram;
	}
	
	public void show() {
		JFrame frame = new JFrame(diagram.toString());
		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(diagram.createImage(), 0, 0, null);
			}
		};
		
		
	}
	
}
