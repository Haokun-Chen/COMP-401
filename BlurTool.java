package a8;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

public class BlurTool implements Tool{
	private ImageEditorModel model;
	private BlurToolUI ui;
	private int blur_size;
	
	public BlurTool(ImageEditorModel model){
		this.model = model;
		ui = new BlurToolUI();
		blur_size = this.ui.getSlider().getValue();
		ui.getSlider().addChangeListener(this);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (this.model.getStack().isEmpty()) {
			this.model.push(this.model.getCurrent());
		}
		model.blur(e.getX(), e.getY(), blur_size);
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
		// TODO Auto-generated method stub
		if (this.model.getStack().isEmpty()) {
			this.model.push(this.model.getCurrent());
		}
		model.blur(e.getX(), e.getY(), blur_size);
		this.model.push(this.model.getCurrent());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		blur_size = this.ui.getSlider().getValue();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Blur";
	}

	@Override
	public JPanel getUI() {
		// TODO Auto-generated method stub
		return ui;
	}

}
