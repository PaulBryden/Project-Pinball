package view;

import model.IGizmo;

import java.awt.Graphics;
import java.util.Observer;

public interface IViewGizmo <E> extends Observer{

	Graphics GetViewObject();

	void paint(Graphics g);

	void setGizmo(IGizmo gizmo);
}
