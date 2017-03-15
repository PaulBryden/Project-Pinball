package model;

/**
 * 
 * An action for triggering the absorb functionality of an absorber.
 * 
 * @author David
 *
 */
class AbsorbAction implements IAction {

	private IAbsorber a;

	/**
	 * 
	 * @param a
	 *            The absorber acted upon
	 */
	public AbsorbAction(IAbsorber a) {
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
