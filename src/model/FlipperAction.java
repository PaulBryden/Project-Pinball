package model;

/**
 * 
 * @author David
 *
 */
class FlipperAction implements IAction {

	private IFlipper flipper;

	/**
	 * 
	 * @param flipper
	 *            The flipper to be triggered by this action.
	 */
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
