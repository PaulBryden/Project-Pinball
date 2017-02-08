package controller;

import view.Board;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.BoardFileHandler;
import model.GameModel;
import model.GizmoList;

public class LoadBoardListener implements ActionListener{
    private MainWindow mainWindow;
GizmoList gizmoList;
GameModel gameloop;
Board gameBoard;
BoardFileHandler boardHandler;

    public LoadBoardListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.showLoadDialog();
    }
}
