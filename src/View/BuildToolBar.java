package View;

import Controller.DeleteGizmoListener;
import Controller.PlaceGizmoListener;

import javax.swing.*;

class BuildToolBar extends JToolBar{
    BuildToolBar(GUI gui){
        super("Build Mode");
        JButton placeBtn = new JButton("Place Gizmo");
        JButton deleteBtn = new JButton("Delete Gizmo");
        JButton rotateBtn = new JButton("Rotate");
        JButton moveBtn = new JButton("Move");
        JButton connectBtn = new JButton("Connect");

        placeBtn.addActionListener(new PlaceGizmoListener(gui));
        deleteBtn.addActionListener(new DeleteGizmoListener(gui));

        setFloatable(false);
        setRollover(true);

        add(placeBtn);
        add(deleteBtn);
        add(rotateBtn);
        add(moveBtn);
        add(connectBtn);
    }
}
