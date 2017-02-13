package model;

public class FlipperAction implements IAction {
	
	private IFlipper flipper;
	
	public FlipperAction(IFlipper flipper) {
		this.flipper = flipper;
	}

	@Override
	public void performAction() {
		flipper.toggleOpen();
	}

	@Override
	public void performAction(IBall ball) {
		performAction();
	}

}
