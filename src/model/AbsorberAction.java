package model;

import java.util.ArrayList;

public class AbsorberAction implements IAction{
	GizmoList gizmoList;
	IGizmo gizmoTrack;
	ArrayList<IBall> storedBalls;
	public AbsorberAction(GizmoList gizmoList, IGizmo gizmoTrack){
		this.gizmoList=gizmoList;
	}
	@Override
	public void performAction() {
		
	}

	@Override
	public void performAction(IBall ball) {
		if(storedBalls.size()>0){
			gizmoList.addGizmo(storedBalls.get(0));
			storedBalls.remove(0);
			storedBalls.add(ball);
		}else{
			storedBalls.add(ball);
		}
	}

}
