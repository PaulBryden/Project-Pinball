package model;

public class AbsorberFireAction implements IAction {
	
	private Absorber a;
	
	public AbsorberFireAction(Absorber a) {
		this.a = a;
	}

	@Override
	public void performAction() {
		a.fireBall();
	}

	@Override
	public void performAction(IBall ball) {
		// TODO Auto-generated method stub

	}

}
