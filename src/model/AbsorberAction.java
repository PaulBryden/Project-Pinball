package model;

import java.util.ArrayList;
import java.util.List;

public class AbsorberAction implements IAction{
	List<IGizmo> gizmos;
	IGizmo gizmoTrack;
	ArrayList<IBall> storedBalls;
	public AbsorberAction(List<IGizmo> gizmos, IGizmo gizmoTrack){
		this.gizmos = gizmos;
	}
	@Override
	public void performAction() {
		
	}

	@Override
	public void performAction(IBall ball) {
		if(storedBalls.size()>0){
			gizmos.add(storedBalls.get(0));
			storedBalls.remove(0);
			storedBalls.add(ball);
		}else{
			storedBalls.add(ball);
		}
	}

}
