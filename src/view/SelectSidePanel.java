package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.IGizmo;

public class SelectSidePanel extends SidePanel {
	
	private static final long serialVersionUID = 2892927258047811490L;

	public SelectSidePanel() {
		super("Select a gizmo to view or edit its properties.");
	}
	
	public SelectSidePanel(IGizmo gizmo) {
		super();
		JPanel propertiesPanel = new JPanel();
		propertiesPanel.setLayout(new GridLayout(1, 2));
		propertiesPanel.add(new JLabel("ID:"));
		propertiesPanel.add(new JLabel(gizmo.getID()));
		JPanel panel = createTitledPanel("Gizmo", 1, propertiesPanel);
		build(panel);
	}

}
