package a8;

import java.util.Stack;

public class ImageEditorModel {

	private Picture original;
	private ObservablePicture current;
	private Pixel target;
	private Pixel temp;
	private Stack<Picture> previous_pic;
	private int opacity;

	public ImageEditorModel(Picture p) {
		original = p;
		current = original.copy().createObservable();
		target = new ColorPixel(0, 0, 0);
		temp = new ColorPixel(0, 0, 0);
		previous_pic = new Stack<Picture>();
		push(current);
	}

	public ObservablePicture getCurrent() {
		return current;
	}

	public Picture getOriginal() {
		return this.original;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void setTarget(Pixel p) {
		this.target = p;
	}

	public Pixel getTarget() {
		return this.target;
	}

	public void setTemp(Pixel p) {
		this.temp = p;
	}

	public Pixel getTemp() {
		return temp;
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size) {
		current.suspendObservable();

		for (int xpos = x - brush_size + 1; xpos <= x + brush_size - 1; xpos++) {
			for (int ypos = y - brush_size + 1; ypos <= y + brush_size - 1; ypos++) {
				if (xpos >= 0 && xpos < current.getWidth() && ypos >= 0 && ypos < current.getHeight()) {
					Pixel temp = blend(brushColor, current.getPixel(xpos, ypos), opacity);
					current.setPixel(xpos, ypos, temp);
				}
			}
		}
		current.resumeObservable();
	}

	public void blur(int x, int y, int blur_size) {
		int xoff = x - blur_size, yoff = y - blur_size;
		if (xoff < 0) {
			xoff = 0;
		}
		if (yoff < 0) {
			yoff = 0;
		}
		if (x + blur_size > this.getCurrent().getWidth()) {
			xoff = this.getCurrent().getWidth() - 2 * blur_size - 1;
		}
		if (y + blur_size > this.getCurrent().getHeight()) {
			yoff = this.getCurrent().getHeight() - 2 * blur_size - 1;
		}

		blurPic(current, 1, xoff, yoff, blur_size);
		// blurPic(this.getCurrent(),2);

	}

	private void blurPic(Picture p, int factor, int xoff, int yoff, int blur_size) {
		// factor = 3;
		// System.out.println("ee");
		int xend = xoff + blur_size * 2 + 1;
		int yend = yoff + blur_size * 2 + 1;
		if (xend > current.getWidth()) {
			xend = current.getWidth();
		}
		if (yend > current.getHeight()) {
			yend = current.getHeight();
		}

		for (int y = yoff; y < yend; y++) {
			for (int x = xoff; x < xend; x++) {
				int left, up, right, down, size;
				double red = 0, green = 0, blue = 0;
				if (x - factor >= 0) {
					left = x - factor;
				} else {
					left = 0;
				}
				if (x + factor <= p.getWidth() - 1) {
					right = x + factor;
				} else {
					right = p.getWidth() - 1;
				}
				if (y - factor >= 0) {
					up = y - factor;
				} else {
					up = 0;
				}
				if (y + factor <= p.getHeight() - 1) {
					down = y + factor;
				} else {
					down = p.getHeight() - 1;
				}
				size = (right - left + 1) * (down - up + 1);
				for (int j = up; j <= down; j++) {
					for (int i = left; i <= right; i++) {
						red += p.getPixel(i, j).getRed();
						blue += p.getPixel(i, j).getBlue();
						green += p.getPixel(i, j).getGreen();
					}
				}
				p.setPixel(x, y, new ColorPixel(red / size, green / size, blue / size));
			}
		}
	}

	public void push(Picture p) {
		this.previous_pic.push(p.copy());
	}

	public Picture pop() {
		return this.previous_pic.pop();
	}

	public Stack<Picture> getStack() {
		return this.previous_pic;
	}

	public void setOpacity(int p) {
		this.opacity = p;
	}

	private Pixel blend(Pixel p1, Pixel p2, int opacity) {
		double red = p1.getRed() * opacity / 100.0 + p2.getRed() * (1 - (opacity / 100.0));
		double green = p1.getGreen() * opacity / 100.0 + p2.getGreen() * (1 - (opacity / 100.0));
		double blue = p1.getBlue() * opacity / 100.0 + p2.getBlue() * (1 - (opacity / 100.0));
		return new ColorPixel(red, green, blue);
	}
}
