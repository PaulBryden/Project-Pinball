package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeToggleListener implements ActionListener {
    private MainWindow mainWindow;

    public ModeToggleListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().getMouseListener().setState(BoardMouseListener.STATE.IDLE);
        mainWindow.getBoard().getMouseListener().setGizmo(BoardMouseListener.CUR_GIZMO.NONE);
        mainWindow.toggleView();
    }
}
