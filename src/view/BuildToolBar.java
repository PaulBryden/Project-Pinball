package view;

import controller.*;

import javax.swing.JButton;
import javax.swing.JToolBar;

class BuildToolBar extends JToolBar{
    BuildToolBar(MainWindow mainWindow){
        super("Build Mode");
        JButton placeBtn = new JButton("Place Gizmo");
        JButton deleteBtn = new JButton("Delete Gizmo");
        JButton rotateBtn = new JButton("Rotate Gizmo");
        JButton moveBtn = new JButton("Move");
        JButton connectGizmoBtn = new JButton("Connect Gizmo");
        JButton keyConnectBtn = new JButton("Key Connect");
        JButton removeConnectionBtn = new JButton("Remove Connection");
        JButton gravBtn = new JButton("Adjust Gravity/Friction");

        placeBtn.addActionListener(new AddGizmoListener(mainWindow));
        deleteBtn.addActionListener(new DeleteGizmoListener(mainWindow));
        rotateBtn.addActionListener(new RotateGizmoListener(mainWindow));
        moveBtn.addActionListener(new MoveGizmoListener(mainWindow));
        connectGizmoBtn.addActionListener(new LinkGizmosListener(mainWindow));
        keyConnectBtn.addActionListener(new AddKeyTriggerListener(mainWindow));
        removeConnectionBtn.addActionListener(new RemoveConnectionListener(mainWindow));
        gravBtn.addActionListener(new ChangeGravFrictionListener(mainWindow));

        setFloatable(false);
        setRollover(true);

        add(placeBtn);
        add(deleteBtn);
        add(rotateBtn);
        add(moveBtn);
        add(connectGizmoBtn);
        add(keyConnectBtn);
        add(removeConnectionBtn);
        add(gravBtn);
    }
}
