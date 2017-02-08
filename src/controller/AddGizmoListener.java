package controller;

import view.MainWindow;
import view.AddGizmoToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGizmoListener implements ActionListener{
    private MainWindow mainWindow;

    public AddGizmoListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(mainWindow.getSideToolBar() instanceof AddGizmoToolBar)) {
            mainWindow.addSideToolBar(new AddGizmoToolBar());
        }
    }
}
