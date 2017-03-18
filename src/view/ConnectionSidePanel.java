
package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.PrimaryActionListener;

public class ConnectionSidePanel extends SidePanel {

	private static final long serialVersionUID = 5258214590215188225L;

	public ConnectionSidePanel(PrimaryActionListener listener) {
		super();
		JButton connectGizmoBtn = ButtonFactory.createButton("add_connection", "Add a connection between gizmos",
				listener);
		JButton disconnectGizmoBtn = ButtonFactory.createButton("remove_connection",
				"Remove a connection between gizmos", listener);
		JButton connectKeyBtn = ButtonFactory.createButton("add_key", "Add a key connection to a gizmo", listener);
		JButton disconnectKeyBtn = ButtonFactory.createButton("remove_key", "Remove a key connection", listener);
		JPanel gizmoPanel = createTitledPanel("Gizmo connections", 2, connectGizmoBtn, disconnectGizmoBtn);
		JPanel keyPanel = createTitledPanel("Key connections", 2, connectKeyBtn, disconnectKeyBtn);
		build(gizmoPanel, keyPanel);
	}

}
