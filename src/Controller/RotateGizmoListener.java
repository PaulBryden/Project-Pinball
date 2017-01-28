package Controller;

import View.DeleteGizmoToolBar;
import View.GUI;
import View.RotateGizmoToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotateGizmoListener implements ActionListener{
    private GUI gui;

    public RotateGizmoListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(gui.getSideToolBar() instanceof RotateGizmoToolBar)) {
            gui.addSideToolBar(new RotateGizmoToolBar());
        }
    }
}
