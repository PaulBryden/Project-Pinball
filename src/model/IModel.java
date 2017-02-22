package model;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Observer;

public interface IModel {
	
	public void tick();
	
	public List<IGizmo> getGizmos();
	
	public List<IBall> getBalls();
	
	public List<IWall> getWalls();
	
	public void addGizmo(IGizmo gizmo);

	public void addBall(IBall ball);

	public void removeGizmo(IGizmo gizmo);
	
	public void addObserver(Observer o);

	public void processKeyPressedTrigger(int keyChar);

	public void processKeyReleasedTrigger(int keyChar);
	
	public void addKeyPressedTrigger(int keyCode, IGizmo gizmo);
	
	public void addKeyReleasedTrigger(int keyCode, IGizmo gizmo);
	
	public void reset();
	
	public Color getBackgroundColour();
	
	public void setBackgroundColour(Color colour);

	public Map<Integer, ITrigger> getKeyPressedTriggers();

	public Map<Integer, ITrigger> getKeyReleasedTriggers();

}
