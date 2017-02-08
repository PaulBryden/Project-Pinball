package model;

import java.util.ArrayList;

import physics.Vect;

public interface IGizmoPhysics { //PHYSICS KEEPS TRACK OF SPEED AND COLLISIONS
									//MODEL BACKEND IS PHYSICS OBJECTS

	void moveGizmo(IGizmo gizmo, ArrayList<IGizmo> gizmoList,CollisionDetails collisions);
	void moveGizmoForTime(IGizmo gizmo);
	Vect getVelocity();
	Vect setVelocity(Vect vel);
}
