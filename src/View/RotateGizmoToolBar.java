package View;

import javax.swing.*;
import java.awt.*;

public class RotateGizmoToolBar extends JToolBar {
    public RotateGizmoToolBar(){
        super("Rotate Gizmo Toolbar");
        JButton leftBtn = new JButton("Left");
        JButton rightBtn = new JButton("Right");
        JButton oneEightyBtn = new JButton("180");

        setLayout(new GridBagLayout());
        setFloatable(false);
        setRollover(true);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        constraints.gridy = 0;
        add(leftBtn, constraints);

        constraints.gridy = 1;
        add(rightBtn, constraints);

        constraints.gridy = 2;
        add(oneEightyBtn, constraints);
    }
}
