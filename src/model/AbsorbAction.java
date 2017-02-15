package model;

public class AbsorbAction implements IAction {

	private Absorber a;
	
	public AbsorbAction(Absorber a) {
		this.a = a;
	}

	@Override
	public void performAction() {

	}

	@Override
	public void performAction(IBall ball) {
		a.absorbBall(ball);
	}

}
