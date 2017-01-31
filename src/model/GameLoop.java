package model;

import java.util.ArrayList;
import java.util.List;

public class GameLoop {

private GizmoList listOfGizmos;
private boolean pauseGame=false;

public GameLoop(){
	listOfGizmos = new GizmoList();
}

public void tick(){
//Evaluate collisions for all items in Gizmolist
//Use smallest tick time until next collision.
//Move all items based on that tick time
}

public GizmoList getGizmoList(){
	return listOfGizmos;
}

public void updateGizmoList(GizmoList gizmos){
	this.listOfGizmos=gizmos;
}

public void addGizmo(IGizmo gizmo){
	listOfGizmos.addGizmo(gizmo);
}

public void removeGizmo(IGizmo gizmo){

	listOfGizmos.removeGizmo(gizmo);
	
}

}


