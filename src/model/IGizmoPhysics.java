package model;

import java.util.ArrayList;

import physics.Vect;

public interface IGizmoPhysics {

	void moveGizmo(IGizmo gizmo, ArrayList<IGizmo> gizmoList,CollisionDetails collisions);
	void moveGizmoForTime(IGizmo gizmo);
	Vect getVelocity();
}
