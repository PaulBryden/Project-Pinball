package view;

import controller.PrimaryActionListener;

public class ClientToolBar extends AbstractToolBar {

	private static final long serialVersionUID = -6104434962760638746L;

	ClientToolBar(PrimaryActionListener listener) {
		super("Client Mode", listener);
	}

	@Override
	protected void populateButtons() {
		addButton("disconnect", "Disconnect");
	}

}
