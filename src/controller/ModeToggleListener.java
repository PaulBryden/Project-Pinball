package controller;

import view.Board;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.CUR_GIZMO.NONE;
import static view.STATE.BUILD;
import static view.STATE.RUN;


public class ModeToggleListener implements ActionListener {
    private MainWindow mainWindow;

    public ModeToggleListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Board board = mainWindow.getBoard();

        board.setState(board.getState().equals(RUN) ? BUILD : RUN);
        board.setSelectedGizmo(NONE);
        mainWindow.toggleView();
    }
}
