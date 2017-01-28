package View;

import Controller.DeleteGizmoListener;
import Controller.PlaceGizmoListener;
import Controller.RotateGizmoListener;

import javax.swing.*;

class BuildToolBar extends JToolBar{
    BuildToolBar(GUI gui){
        super("Build Mode");
        JButton placeBtn = new JButton("Place Gizmo");
        JButton deleteBtn = new JButton("Delete Gizmo");
        JButton rotateBtn = new JButton("Rotate Gizmo");
        JButton moveBtn = new JButton("Move");
        JButton connectGizmoBtn = new JButton("Connect Gizmo");
        JButton keyConnectBtn = new JButton("Key Connect");

        placeBtn.addActionListener(new PlaceGizmoListener(gui));
        deleteBtn.addActionListener(new DeleteGizmoListener(gui));
        rotateBtn.addActionListener(new RotateGizmoListener(gui));

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
