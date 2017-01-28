package Controller;

import View.GUI;
import View.GizmoToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGizmoListener implements ActionListener{
    private GUI gui;

    public AddGizmoListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(gui.getSideToolBar() instanceof GizmoToolBar)) {
            gui.addSideToolBar(new GizmoToolBar());
        }
    }
}
