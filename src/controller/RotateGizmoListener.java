package controller;

import view.MainWindow;
import view.RotateGizmoToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.STATE.ROTATE;

public class RotateGizmoListener implements ActionListener{
    private MainWindow mainWindow;

    public RotateGizmoListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(mainWindow.getSideToolBar() instanceof RotateGizmoToolBar)) {
            mainWindow.addSideToolBar(new RotateGizmoToolBar());
            mainWindow.getBoard().getMouseListener().setState(ROTATE);
            mainWindow.setStatusLabel("Rotating Gizmo(s)");
        }
    }
}
