package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.PrimaryActionListener;

public class DeleteGizmoSidePanel extends SidePanel {

	private static final long serialVersionUID = -7603246779725615572L;

	public DeleteGizmoSidePanel(PrimaryActionListener listener) {
		super();
		JButton deleteAllButton = new JButton("Delete all");
		deleteAllButton.addActionListener(listener);
		deleteAllButton.setActionCommand("delete_all");
		JPanel deleteAllPanel = createTitledPanel("Reset", 1, deleteAllButton);
		build("Click on a gizmo to delete it.", deleteAllPanel);
	}
}
