package Controller;

import View.DeleteGizmoToolBar;
import View.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteGizmoListener implements ActionListener{
    private GUI gui;

    public DeleteGizmoListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(gui.getSideToolBar() instanceof DeleteGizmoToolBar)) {
            gui.addSideToolBar(new DeleteGizmoToolBar());
        }
    }
}
