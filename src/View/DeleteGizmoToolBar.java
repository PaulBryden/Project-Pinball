package View;

import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class DeleteGizmoToolBar extends JToolBar{
    public DeleteGizmoToolBar() {
        super("Delete Gizmo Toolbar");
        JButton confirm = new JButton("Delete");
        JButton cancel = new JButton("Cancel");

        setLayout(new GridBagLayout());
        setFloatable(false);
        setRollover(true);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        constraints.gridy = 0;
        add(confirm, constraints);

        constraints.gridy = 1;
        add(cancel, constraints);
    }
}
