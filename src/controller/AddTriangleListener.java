package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.TRIANGLE;

public class AddTriangleListener implements ActionListener{
    private MainWindow mainWindow;

    public AddTriangleListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().getMouseListener().setGizmo(TRIANGLE);
        mainWindow.setStatusLabel("Adding Triangle");
    }
}