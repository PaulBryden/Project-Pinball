package view;

import controller.PrimaryActionListener;

public class ConnectionSidePanel extends SidePanel {
	
	private static final long serialVersionUID = 5258214590215188225L;
	
	public ConnectionSidePanel(PrimaryActionListener listener) {
		super();
		this.add(ButtonFactory.createButton("add_connection", "Add a connection between gizmos", listener));
		this.add(ButtonFactory.createButton("remove_connection", "Remove a connection between gizmos", listener));
	}
	
}
