package controller;

import view.Board;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.BoardFileHandler;
import model.GameModel;
import model.IGizmo;

public class LoadBoardListener implements ActionListener{
    private MainWindow mainWindow;
    private List<IGizmo> gizmoList;
    private GameModel gameloop;
    private Board gameBoard;
    private BoardFileHandler boardHandler;

    public LoadBoardListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.revalidate();
        mainWindow.repaint();
    }
}
