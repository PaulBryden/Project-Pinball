
package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import controller.PrimaryActionListener;

public class ConnectionSidePanel extends SidePanel {

	private static final long serialVersionUID = 5258214590215188225L;
	private JPanel keyConnectionsPanel;
	private JLabel gizmoIdLabel;
	private JLabel keyConnectionsLabel;
	private JLabel triggersLabel;
	private JLabel triggeredByLabel;

	public ConnectionSidePanel(PrimaryActionListener listener, MainWindow mainWindow) {
		super();
		JToggleButton connectGizmoBtn = ButtonFactory.createToggleButton("add_connection",
				"Add a connection between gizmos", listener);
		JToggleButton disconnectGizmoBtn = ButtonFactory.createToggleButton("remove_connection",
				"Remove a connection between gizmos", listener);
		JToggleButton connectKeyBtn = ButtonFactory.createToggleButton("add_key", "Add a key connection to a gizmo",
				listener);
		JToggleButton disconnectKeyBtn = ButtonFactory.createToggleButton("remove_key", "Remove a key connection",
				listener);
		ButtonFactory.makeToggleButtonsExclusive(connectGizmoBtn, disconnectGizmoBtn, connectKeyBtn, disconnectKeyBtn);
		JPanel gizmoPanel = createTitledPanel("Gizmo connections", 2, connectGizmoBtn, disconnectGizmoBtn);
		JPanel keyPanel = createTitledPanel("Key connections", 2, connectKeyBtn, disconnectKeyBtn);
		gizmoIdLabel = new JLabel("");
		keyConnectionsLabel = new JLabel("");
		triggersLabel = new JLabel("");
		triggeredByLabel = new JLabel("");
		Font f = gizmoIdLabel.getFont().deriveFont(Font.PLAIN);
		gizmoIdLabel.setFont(f);
		keyConnectionsLabel.setFont(f);
		triggersLabel.setFont(f);
		triggeredByLabel.setFont(f);
		keyConnectionsPanel = createTitledPanel("Existing connections", 2, new JLabel("Gizmo ID:"), gizmoIdLabel,
				new JLabel("Keys:"), keyConnectionsLabel, new JLabel("Triggers:"), triggersLabel,
				new JLabel("Triggered:"), triggeredByLabel);
		keyConnectionsPanel.setVisible(false);
		build("Click on a gizmo to view its connections.", gizmoPanel, keyPanel, keyConnectionsPanel);
	}

	public void setKeyConnectionsVisible(boolean visible) {
		keyConnectionsPanel.setVisible(visible);
		this.setInstructions(visible ? "" : "Click on a gizmo to view its connections.");
	}

	public void setExistingConnectionInfo(String id, String keyConnections, String triggers, String triggeredBy) {
		this.gizmoIdLabel.setText(id);
		this.keyConnectionsLabel.setText("<html>" + keyConnections + "</html>");
		this.triggersLabel.setText("<html>" + triggers + "</html>");
		this.triggeredByLabel.setText("<html>" + triggeredBy + "</html>");
	}

}
