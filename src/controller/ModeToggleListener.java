package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.NONE;
import static controller.BoardMouseListener.STATE.BUILD;
import static controller.BoardMouseListener.STATE.RUN;

public class ModeToggleListener implements ActionListener {
    private MainWindow mainWindow;

    public ModeToggleListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BoardMouseListener mouseListener = mainWindow.getBoard().getMouseListener();

        mouseListener.setState(mouseListener.getState().equals(RUN) ? BUILD : RUN);
        mouseListener.setGizmo(NONE);
        mainWindow.toggleView();
    }
}
