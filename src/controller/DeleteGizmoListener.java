package controller;

import view.DeleteGizmoToolBar;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.STATE.REMOVE;

public class DeleteGizmoListener implements ActionListener{
    private MainWindow mainWindow;

    public DeleteGizmoListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(mainWindow.getSideToolBar() instanceof DeleteGizmoToolBar)) {
            mainWindow.addSideToolBar(new DeleteGizmoToolBar());
            mainWindow.getBoard().getMouseListener().setState(REMOVE);
            mainWindow.setStatusLabel("Deleting Gizmo(s)");
        }
    }
}
