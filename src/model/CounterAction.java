package model;

public class CounterAction implements IAction {
	
	private ICounterGizmo gizmo;
	
	public CounterAction(ICounterGizmo gizmo) {
		this.gizmo = gizmo;
	}

	@Override
	public void performAction() {
		gizmo.increment();
	}

	@Override
	public void performAction(IBall ball) {
		performAction();
	}

}
