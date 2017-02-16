package model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class KeyTrigger implements ITrigger {
	
	private Set<IGizmo> gizmos;
	
	public KeyTrigger() {
		this.gizmos = new HashSet<>();
	}

	public KeyTrigger(IGizmo gizmo) {
		this();
		addGizmoToTrigger(gizmo);
	}

	@Override
	public void addGizmoToTrigger(IGizmo gizmo) {
		gizmos.add(gizmo);
	}

	@Override
	public Set<IGizmo> getGizmosToTrigger() {
		return gizmos;
	}

	@Override
	public void triggerConnectedGizmos() {
		for (IGizmo gizmo : gizmos) {
			gizmo.performActions();
		}
	}

}
