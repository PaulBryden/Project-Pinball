package View;

import Controller.AddGizmoListener;

import javax.swing.*;

class BuildToolBar extends JToolBar{
    BuildToolBar(GUI gui){
        super("Build Mode");
        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove");
        JButton rotateBtn = new JButton("Rotate");
        JButton moveBtn = new JButton("Move");
        JButton connectBtn = new JButton("Connect");

        addBtn.addActionListener(new AddGizmoListener(gui));


        setFloatable(false);
        setRollover(true);

        add(addBtn);
        add(removeBtn);
        add(rotateBtn);
        add(moveBtn);
        add(connectBtn);
    }
}
