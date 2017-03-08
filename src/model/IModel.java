package model;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import physics.Vect;

public interface IModel {
	
	public void tick();
	
	public List<IGizmo> getGizmos();
	
	public List<IBall> getBalls();
	
	public void addGizmo(IGizmo gizmo);

	public void addBall(IBall ball);
	
	public void removeBall(IBall ball);

	public void removeGizmo(IGizmo gizmo);

	public IGizmo getGizmo(Vect coords);
	
	public IBall getBall(Vect coords);
	
	public boolean isCellEmpty(Vect coords);
	
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
