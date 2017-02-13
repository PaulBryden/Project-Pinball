package model;

import java.util.List;

public interface ITrigger {

	public void addGizmoToTrigger(IGizmo gizmo);
	
	public List<IGizmo> getGizmosToTrigger();

	public void triggerConnectedGizmos();
	
}
