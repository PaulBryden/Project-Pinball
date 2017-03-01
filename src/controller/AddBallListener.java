package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.BALL;

public class AddBallListener implements ActionListener{
    private MainWindow mainWindow;

    public AddBallListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().getMouseListener().setGizmo(BALL);
        mainWindow.setStatusLabel("Adding Ball");
    }
}
