package view;

import java.awt.Graphics;
import java.util.Observer;

public interface IViewGizmo extends Observer{

	Graphics GetViewObject();

	void paint(Graphics g);
}
