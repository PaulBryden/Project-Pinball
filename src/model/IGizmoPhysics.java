package model;

public interface IGizmoPhysics {

	void moveGizmo(IGizmo gizmo,CollisionDetails collisions);
	void moveGizmoForTime(IGizmo gizmo);
}
