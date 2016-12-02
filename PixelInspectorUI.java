package a8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorUI extends JPanel {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private JButton copy_button;
	private JPanel main_panel;
	private PictureView glass;

	public PixelInspectorUI() {
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");
		copy_button = new JButton("Copy");

		Picture p = new PictureImpl(100, 100);
		glass = new PictureView(p.createObservable());

		main_panel = new JPanel();
		main_panel.setLayout(new GridLayout(3, 1));
		main_panel.add(x_label);
		main_panel.add(y_label);
		main_panel.add(pixel_info);

		this.setLayout(new GridLayout(1, 0));
		this.add(main_panel);
		this.add(copy_button);
		this.add(glass);

	}

	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));
	}

	public void setGlass(Picture p) {
		for (int i = 0; i < this.glass.getPicture().getHeight(); i++) {
			for (int j = 0; j < this.glass.getPicture().getWidth(); j++) {
				this.glass.getPicture().setPixel(j, i, p.getPixel(j, i));
			}
		}
	}

	public JButton getButton() {
		return this.copy_button;
	}
}
