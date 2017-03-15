package model;

/**
 * 
 * Action for triggering the fire functionality of an absorber.
 * 
 * @author David
 *
 */
class AbsorberFireAction implements IAction {

	private IAbsorber a;

	/**
	 * 
	 * @param a
	 *            The absorber acted upon
	 */
	public AbsorberFireAction(IAbsorber a) {
		this.a = a;
	}

	@Override
	public void performAction() {
		a.fireBall();
	}

	@Override
	public void performAction(IBall ball) {
		performAction();
	}

}
