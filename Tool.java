package a8;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

public interface Tool extends MouseListener, MouseMotionListener,ActionListener,ChangeListener {
	String getName();
	JPanel getUI();
}
