package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlurToolUI extends JPanel {
	private JSlider blur_size_slider;

	public BlurToolUI() {
		setLayout(new GridLayout(0, 1));
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout());

		JLabel blur_label = new JLabel("Blur Size: ");
		blur_size_slider = new JSlider(0, 50);
		blur_size_slider.setMajorTickSpacing(10);
		blur_size_slider.setPaintTicks(true);
		blur_size_slider.setPaintLabels(true);

		main_panel.add(blur_label, BorderLayout.WEST);
		main_panel.add(blur_size_slider, BorderLayout.CENTER);
		add(main_panel);

	}

	public JSlider getSlider() {
		return this.blur_size_slider;
	}

}
