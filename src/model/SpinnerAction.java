package model;

/**
 * 
 * @author David
 *
 */
class SpinnerAction implements IAction {

	private ISpinner spinner
	;

	/**
	 * 
	 * @param spinner
	 *            The spinner to be triggered by this action.
	 */
	public SpinnerAction(IFlipper flipper) {
		this.spinner = spinner;
	}

	@Override
	public void performAction() {
		spinner.toggleDirection();
	}

	@Override
	public void performAction(IBall ball) {
		performAction();
	}

}
