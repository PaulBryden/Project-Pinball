package controller;

import view.Board;
import view.MainWindow;
import view.AddGizmoToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGizmoListener implements ActionListener{
    private MainWindow mainWindow;
    private Board board;

    public AddGizmoListener(MainWindow mainWindow, Board board){
        this.mainWindow = mainWindow;
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(mainWindow.getSideToolBar() instanceof AddGizmoToolBar)) {
            mainWindow.addSideToolBar(new AddGizmoToolBar(board));
            mainWindow.getBoard().getMouseListener().setState(BoardMouseListener.STATE.ADD);
        }
    }
}
