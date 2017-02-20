package view;

import java.awt.Graphics;
import java.util.Observer;

public interface IViewGizmo extends Observer{
	void paint(Graphics g);
}
