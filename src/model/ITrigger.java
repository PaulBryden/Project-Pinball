package model;

import java.util.Set;

public interface ITrigger {

	public void addGizmoToTrigger(IGizmo gizmo);
	
	public Set<IGizmo> getGizmosToTrigger();

	public void triggerConnectedGizmos();
	
}
