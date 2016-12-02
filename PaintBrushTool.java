package a8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintBrushTool implements Tool {

	private PaintBrushToolUI ui;
	private ImageEditorModel model;
	private int brush_size = 5;

	public PaintBrushTool(ImageEditorModel model) {
		this.model = model;
		ui = new PaintBrushToolUI();
		this.model.setOpacity(this.ui.getOpacitySlider().getValue());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.model.getStack().isEmpty()) {
			this.model.push(this.model.getCurrent());
		}
		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), brush_size);
		this.model.push(this.model.getCurrent());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.model.getStack().isEmpty()) {
			this.model.push(this.model.getCurrent());
		}
		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), brush_size);
		this.model.push(this.model.getCurrent());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "Paint Brush";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

	public void copyPixel(Pixel p) {
		this.ui.copyPixel(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (!this.model.getStack().isEmpty() && !isEqual(this.model.getCurrent(), this.model.getOriginal())) {
			Picture p = this.model.pop();
			if (isEqual(p, this.model.getCurrent())) {
				p = this.model.pop();
			}
			for (int i = 0; i < p.getHeight(); i++) {
				for (int j = 0; j < p.getWidth(); j++) {
					this.model.getCurrent().setPixel(j, i, p.getPixel(j, i));
				}
			}
		}
	}

	public void addActionListener(ActionListener l) {
		this.ui.getButton().addActionListener(l);
	}

	public void addChangeListener(ChangeListener l) {
		this.ui.getSizeSlider().addChangeListener(l);
		this.ui.getOpacitySlider().addChangeListener(l);
	}

	private boolean isEqual(Picture p1, Picture p2) {
		for (int i = 0; i < p1.getHeight(); i++) {
			for (int j = 0; j < p1.getWidth(); j++) {
				Pixel p_1 = p1.getPixel(j, i);
				Pixel p_2 = p2.getPixel(j, i);
				if (p_1.getBlue() != p_2.getBlue() || p_1.getRed() != p_2.getRed()
						|| p_1.getGreen() != p_2.getGreen()) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		brush_size = this.ui.getSizeSlider().getValue();
		int temp = this.ui.getOpacitySlider().getValue();
		this.model.setOpacity(temp);

	}

}
