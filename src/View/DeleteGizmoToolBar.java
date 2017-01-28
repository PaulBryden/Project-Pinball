package View;

import javax.swing.*;
import java.awt.*;

public class DeleteGizmoToolBar extends JToolBar{
    public DeleteGizmoToolBar() {
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
