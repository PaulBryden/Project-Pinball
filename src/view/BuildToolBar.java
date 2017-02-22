package view;

import controller.DeleteGizmoListener;
import controller.AddGizmoListener;
import controller.RotateGizmoListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

class BuildToolBar extends JToolBar{
    BuildToolBar(MainWindow mainWindow, Board board){
        super("Build Mode");
        JButton placeBtn = new JButton("Place Gizmo");
        JButton deleteBtn = new JButton("Delete Gizmo");
        JButton rotateBtn = new JButton("Rotate Gizmo");
        JButton moveBtn = new JButton("Move");
        JButton connectGizmoBtn = new JButton("Connect Gizmo");
        JButton keyConnectBtn = new JButton("Key Connect");

        placeBtn.addActionListener(new AddGizmoListener(mainWindow, board));
        deleteBtn.addActionListener(new DeleteGizmoListener(mainWindow));
        rotateBtn.addActionListener(new RotateGizmoListener(mainWindow));

        setFloatable(false);
        setRollover(true);

        add(placeBtn);
        add(deleteBtn);
        add(rotateBtn);
        add(moveBtn);
        add(connectGizmoBtn);
        add(keyConnectBtn);
    }
}
