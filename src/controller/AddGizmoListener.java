package controller;

import view.Board;
import view.MainWindow;
import view.AddGizmoToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.STATE.ADD;

public class AddGizmoListener implements ActionListener{
    private MainWindow mainWindow;

    public AddGizmoListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(mainWindow.getSideToolBar() instanceof AddGizmoToolBar)) {
            mainWindow.addSideToolBar(new AddGizmoToolBar(mainWindow));
            mainWindow.getBoard().getMouseListener().setState(ADD);
            mainWindow.setStatusLabel("Placing Gizmo(s). Please select a gizmo from the left.");
        }
    }
}
