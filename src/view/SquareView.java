package view;

import java.awt.Graphics;

import model.IGizmo;

public class SquareView implements IViewGizmo {

	private IGizmo gizmo;
	private static final int GRID_WIDTH = 20;

	public SquareView(IGizmo gizmo){
		this.gizmo = gizmo;
	}

	public void paint(Graphics g){
		g.setColor(gizmo.getColour());
		g.fillRect((int) gizmo.getGridCoords().x() * GRID_WIDTH ,
				(int) gizmo.getGridCoords().y() * GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
	}

	@Override
	public IGizmo getGizmo() {
		return (gizmo);
	}
	
}
