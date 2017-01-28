package Controller;

import View.GUI;
import View.PlaceGizmoToolBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaceGizmoListener implements ActionListener{
    private GUI gui;

    public PlaceGizmoListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(gui.getSideToolBar() instanceof PlaceGizmoToolBar)) {
            gui.addSideToolBar(new PlaceGizmoToolBar());
        }
    }
}
