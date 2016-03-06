package diagram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImageExporter {
	private BufferedImage image;
	
	public ImageExporter(BufferedImage image) {
		this.image = image;
	}
	
	public void export() {
		JFrame frame = new JFrame("Export image");
		JPanel panel = new JPanel();
		panel.add(new JLabel("File name: "));
		JTextField textField = new JTextField();
		textField.setSize(100,20);
		panel.add(textField);
		JButton exportButton = new JButton("Export");
		exportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				doExport(
			}
		});
		
	}
}