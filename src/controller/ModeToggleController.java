package controller;

import view.Board;
import view.MainWindow;

import static view.CUR_GIZMO.NONE;
import static view.STATE.BUILD;
import static view.STATE.RUN;

public class ModeToggleController {
    private MainWindow mainWindow;

    ModeToggleController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        Board board = mainWindow.getBoard();

        board.setState(board.getState().equals(RUN) ? BUILD : RUN);
        board.setSelectedGizmo(NONE);
        mainWindow.toggleView();
    }
}
