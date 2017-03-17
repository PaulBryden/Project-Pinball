package view;

import controller.RemoveGizmoConnectionListener;
import controller.RemoveKeyConnectionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.*;

import static java.awt.GridBagConstraints.VERTICAL;

public class RemoveConnectionToolbar extends SidePanel{
    public RemoveConnectionToolbar(MainWindow mainWindow){
        JButton removeGizmoBtn = new JButton("Gizmo");
        JButton removeKeyBtn = new JButton("Key");

        setLayout(new GridBagLayout());

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
