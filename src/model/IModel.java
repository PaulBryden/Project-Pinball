package model;

import java.util.List;
import java.util.Observer;

public interface IModel {
	
	public void tick();
	
	public List<IGizmo> getGizmos();
	
	public List<IBall> getBalls();
	
	public List<IWall> getWalls();
	
	public void addGizmo(IGizmo gizmo);
	
	public void removeGizmo(IGizmo gizmo);
	
	public void addObserver(Observer o);

	public void processKeyTrigger(int keyChar);
	
	public void addKeyTrigger(int keyCode, IGizmo gizmo);
	
	public void updateGizmoList(List<IGizmo> gizmos);
	
	public void addBall(IBall ball);

}
