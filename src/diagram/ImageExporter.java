package diagram;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImageExporter {
	private static final String FORMAT = "png";
	private BufferedImage image;
	
	public ImageExporter(BufferedImage image) {
		this.image = image;
		JFrame frame = new JFrame("Export image");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.add(new JLabel("File name: "));
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 20));
		panel.add(textField);
		JButton exportButton = new JButton("Export");
		exportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportImage(textField.getText());
				frame.dispose();
			}
		});
		panel.add(exportButton);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void exportImage(String fileName) {
		File folder = new File("exports");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File("exports" + File.separator + fileName + "." + FORMAT);
		try {
			ImageIO.write(image, FORMAT, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}