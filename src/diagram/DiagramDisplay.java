package diagram;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class DiagramDisplay {
	private Diagram diagram;
	
	public DiagramDisplay(Diagram diagram) {
		this.diagram = diagram;
	}
	
	public void show() {
		JFrame frame = new JFrame(diagram.toString());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(727, 573);
		frame.setLocationRelativeTo(null);
		@SuppressWarnings("serial")
		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(diagram.getImage(), 10, 10, null);
			}
		};
		frame.add(panel);
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem itemExport = new JMenuItem("Export");
		itemExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ImageExporter(diagram.getImage());
			}
		});
		menuFile.add(itemExport);
		menuBar.add(menuFile);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}
}