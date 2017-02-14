package model;

import java.util.LinkedList;
import java.util.List;

public class KeyTrigger implements ITrigger {
	
	private List<IGizmo> gizmos;
	
	public KeyTrigger() {
		this.gizmos = new LinkedList<>();
	}

	@Override
	public void addGizmoToTrigger(IGizmo gizmo) {
		gizmos.add(gizmo);
	}

	@Override
	public List<IGizmo> getGizmosToTrigger() {
		return gizmos;
	}

	@Override
	public void triggerConnectedGizmos() {
		for (IGizmo gizmo : gizmos) {
			gizmo.performActions();
		}
	}

}
