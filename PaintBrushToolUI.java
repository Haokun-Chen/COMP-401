package a8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintBrushToolUI extends JPanel implements ChangeListener {

	private JSlider red_slider;
	private JSlider green_slider;
	private JSlider blue_slider;
	private PictureView color_preview;
	private JButton undo;
	private JSlider size_slider;
	private JSlider opacity_slider;

	public PaintBrushToolUI() {
		setLayout(new GridLayout(0, 1));

		JPanel color_chooser_panel = new JPanel();
		color_chooser_panel.setLayout(new BorderLayout());

		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0, 1));

		JPanel red_slider_panel = new JPanel();
		JLabel red_label = new JLabel("Red:");
		red_slider_panel.setLayout(new BorderLayout());
		red_slider_panel.add(red_label, BorderLayout.WEST);
		red_slider = new JSlider(0, 100);
		red_slider.addChangeListener(this);
		red_slider_panel.add(red_slider, BorderLayout.CENTER);

		JPanel green_slider_panel = new JPanel();
		JLabel green_label = new JLabel("Green:");
		green_slider_panel.setLayout(new BorderLayout());
		green_slider_panel.add(green_label, BorderLayout.WEST);
		green_slider = new JSlider(0, 100);
		green_slider.addChangeListener(this);
		green_slider_panel.add(green_slider, BorderLayout.CENTER);

		JPanel blue_slider_panel = new JPanel();
		JLabel blue_label = new JLabel("Blue: ");
		blue_slider_panel.setLayout(new BorderLayout());
		blue_slider_panel.add(blue_label, BorderLayout.WEST);
		blue_slider = new JSlider(0, 100);
		blue_slider.addChangeListener(this);
		blue_slider_panel.add(blue_slider, BorderLayout.CENTER);

		// Assumes green label is widest and asks red and blue label
		// to be the same.
		Dimension d = green_label.getPreferredSize();
		red_label.setPreferredSize(d);
		blue_label.setPreferredSize(d);

		slider_panel.add(red_slider_panel);
		slider_panel.add(green_slider_panel);
		slider_panel.add(blue_slider_panel);

		color_chooser_panel.add(slider_panel, BorderLayout.CENTER);

		color_preview = new PictureView(new ObservablePictureImpl(new PictureImpl(50, 50)));
		color_chooser_panel.add(color_preview, BorderLayout.EAST);

		JPanel size_panel = new JPanel();
		JLabel size_label = new JLabel("Brush Size: ");
		size_panel.setLayout(new BorderLayout());
		size_panel.add(size_label, BorderLayout.WEST);
		size_slider = new JSlider(1, 10);
		size_slider.setMajorTickSpacing(1);
		size_slider.setPaintTicks(true);
		size_slider.setPaintLabels(true);
		size_panel.add(size_slider, BorderLayout.CENTER);

		JPanel opacity_panel = new JPanel();
		JLabel opacity_label = new JLabel("Opacity: ");
		opacity_panel.setLayout(new BorderLayout());
		opacity_panel.add(opacity_label, BorderLayout.WEST);
		opacity_slider = new JSlider(0, 100);
		opacity_slider.setMajorTickSpacing(10);
		opacity_slider.setValue(100);
		opacity_slider.setPaintTicks(true);
		opacity_slider.setPaintLabels(true);
		opacity_panel.add(opacity_slider, BorderLayout.CENTER);

		undo = new JButton("Undo");
		
		JPanel newFunction = new JPanel();
		newFunction.setLayout(new GridLayout(0, 1));
		newFunction.add(size_panel);
		newFunction.add(opacity_panel);
		newFunction.add(undo);
		
		color_chooser_panel.add(newFunction, BorderLayout.SOUTH);
		add(color_chooser_panel);
		stateChanged(null);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Pixel p = new ColorPixel(red_slider.getValue() / 100.0, green_slider.getValue() / 100.0,
				blue_slider.getValue() / 100.0);
		ObservablePicture preview_frame = color_preview.getPicture();
		preview_frame.suspendObservable();
		for (int x = 0; x < preview_frame.getWidth(); x++) {
			for (int y = 0; y < preview_frame.getHeight(); y++) {
				preview_frame.setPixel(x, y, p);
			}
		}
		preview_frame.resumeObservable();
	}

	public Pixel getBrushColor() {
		return color_preview.getPicture().getPixel(0, 0);
	}

	public void copyPixel(Pixel p) {
		this.blue_slider.setValue((int) (p.getBlue() * 100));
		this.red_slider.setValue((int) (p.getRed() * 100));
		this.green_slider.setValue((int) (p.getGreen() * 100));
	}

	public JButton getButton() {
		return this.undo;
	}

	public JSlider getSizeSlider() {
		return this.size_slider;
	}

	public JSlider getOpacitySlider() {
		return this.opacity_slider;
	}
}
