package model;

import java.util.ArrayList;
import java.util.List;

import physics.Vect;

public class AbsorberAction implements IAction{
	List<IBall> allBalls;
	IGizmo gizmoTrack;
	ArrayList<IBall> storedBalls;
	public AbsorberAction(List<IBall> balls, IGizmo gizmoTrack){
		this.allBalls = balls;
	}
	@Override
	public void performAction() {
		
	}

	@Override
	public void performAction(IBall ball) {
		if(storedBalls.size()>0){
			allBalls.add(storedBalls.get(0));
			storedBalls.remove(0);
			storedBalls.add(ball);
			ball.setVelo(new Vect(0,50));
		}else{
			storedBalls.add(ball);
		}
	}

}
