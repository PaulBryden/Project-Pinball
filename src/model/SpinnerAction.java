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
	public SpinnerAction(Spinner spinner) {
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
