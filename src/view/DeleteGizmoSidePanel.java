package view;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class DeleteGizmoSidePanel extends SidePanel {
    
	private static final long serialVersionUID = -7603246779725615572L;

	public DeleteGizmoSidePanel() {
        super();
        JButton confirm = new JButton("Delete");
        JButton cancel = new JButton("Cancel");

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        constraints.gridy = 0;
        add(confirm, constraints);

        constraints.gridy = 1;
        add(cancel, constraints);
    }
}
