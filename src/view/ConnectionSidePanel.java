package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;

import controller.PrimaryActionListener;

public class ConnectionSidePanel extends SidePanel {
	
	private static final long serialVersionUID = 5258214590215188225L;
	
	public ConnectionSidePanel(PrimaryActionListener listener) {
		super();
		JButton connectGizmoBtn = ButtonFactory.createButton("add_connection", "Add a connection between gizmos", listener);
		JButton disconnectGizmoBtn = ButtonFactory.createButton("remove_connection", "Remove a connection between gizmos", listener);
		JButton connectKeyBtn = ButtonFactory.createButton("add_key", "Add a key connection to a gizmo", listener);
		JButton disconnectKeyBtn = ButtonFactory.createButton("remove_key", "Remove a key connection", listener);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.insets = new Insets(5, 3, 0, 3);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(createTitledButtonPanel("Gizmo connections", connectGizmoBtn, disconnectGizmoBtn), c);
		c.gridy = 1;
		add(createTitledButtonPanel("Key connections", connectKeyBtn, disconnectKeyBtn), c);
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		add(Box.createVerticalGlue(), c);
	}
	
}
