package view;

import controller.RemoveGizmoConnectionListener;
import controller.RemoveKeyConnectionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.*;

public class RemoveConnectionToolbar extends JToolBar{
    public RemoveConnectionToolbar(MainWindow mainWindow){
        super("Remove Connection Toolbar");
        JButton removeGizmoBtn = new JButton("Gizmo");
        JButton removeKeyBtn = new JButton("Key");

        setLayout(new GridBagLayout());
        setFloatable(false);
        setRollover(true);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = VERTICAL;

        constraints.gridy = 0;
        removeGizmoBtn.addActionListener(new RemoveGizmoConnectionListener(mainWindow));
        add(removeGizmoBtn, constraints);

        constraints.gridy = 1;
        removeKeyBtn.addActionListener(new RemoveKeyConnectionListener(mainWindow));
        add(removeKeyBtn, constraints);
    }
}
