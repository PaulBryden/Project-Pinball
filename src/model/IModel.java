package model;

import java.util.List;
import java.util.Observer;

import physics.LineSegment;

public interface IModel {
	
	public void tick();
	
	public List<IGizmo> getGizmos();
	
	public List<IBall> getBalls();
	
	public List<LineSegment> getWalls();
	
	public void addGizmo(IGizmo gizmo);
	
	public void removeGizmo(IGizmo gizmo);
	
	public void addObserver(Observer o);

}
