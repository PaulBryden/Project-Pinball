package view;

import model.IGizmo;

import java.awt.Graphics;

public interface IViewGizmo {
	void paint(Graphics g);

	IGizmo getGizmo();
}
