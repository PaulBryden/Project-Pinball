package model;

import java.util.HashSet;
import java.util.Set;

public class KeyTrigger {
	
	private Set<IGizmo> gizmos;
	
	public KeyTrigger() {
		this.gizmos = new HashSet<>();
	}

	public KeyTrigger(IGizmo gizmo) {
		this();
		addGizmoToTrigger(gizmo);
	}

	public void addGizmoToTrigger(IGizmo gizmo) {
		gizmos.add(gizmo);
	}

	public Set<IGizmo> getGizmosToTrigger() {
		return gizmos;
	}

	public void triggerConnectedGizmos() {
		for (IGizmo gizmo : gizmos) {
			gizmo.performActions();
		}
	}

}
