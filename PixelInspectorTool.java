package a8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

public class PixelInspectorTool implements Tool {

	private PixelInspectorUI ui;
	private ImageEditorModel model;

	public PixelInspectorTool(ImageEditorModel model) {
		this.model = model;
		ui = new PixelInspectorUI();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			Pixel temp = model.getPixel(e.getX(), e.getY());
			ui.setInfo(e.getX(), e.getY(), temp);
			model.setTemp(temp);

			int x = e.getX() - 25, y = e.getY() - 25;
			if (x < 0) {
				x = 0;
			}
			if (y < 0) {
				y = 0;
			}
			if (y + 50 > model.getCurrent().getHeight()) {
				y = model.getCurrent().getHeight() - 50;
			}
			if (x + 50 > model.getCurrent().getWidth()) {
				x = model.getCurrent().getWidth() - 50;
			}

			Picture mag = model.getCurrent().extract(x, y, 50, 50);
			mag = magnify(mag);
			ui.setGlass(mag);
		} catch (Exception ex) {
			// Click may have been out of bounds. Do nothing in this case.
		}
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
	public String getName() {
		return "Pixel Inspector";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		try {
			Pixel temp = model.getPixel(e.getX(), e.getY());
			ui.setInfo(e.getX(), e.getY(), temp);
			model.setTemp(temp);

			int x = e.getX() - 25, y = e.getY() - 25;
			if (x < 0) {
				x = 0;
			}
			if (y < 0) {
				y = 0;
			}
			if (y + 50 > model.getCurrent().getHeight()) {
				y = model.getCurrent().getHeight() - 50;
			}
			if (x + 50 > model.getCurrent().getWidth()) {
				x = model.getCurrent().getWidth() - 50;
			}

			Picture mag = model.getCurrent().extract(x, y, 50, 50);
			mag = magnify(mag);
			ui.setGlass(mag);

		} catch (Exception ex) {
			// Click may have been out of bounds. Do nothing in this case.
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		model.setTarget(model.getTemp());
	}

	public void addActionListener(ActionListener l) {
		this.ui.getButton().addActionListener(l);
	}

	private Picture magnify(Picture p) {
		Picture output = new PictureImpl(p.getWidth() * 2, p.getHeight() * 2);

		for (int i = 0; i < output.getHeight(); i++) {
			for (int j = 0; j < output.getWidth(); j++) {
				Pixel temp = p.getPixel(j / 2, i / 2);
				output.setPixel(j, i, temp);
			}
		}
		return output;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

}
