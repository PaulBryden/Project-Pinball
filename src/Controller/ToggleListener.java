package Controller;

import View.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleListener implements ActionListener {
    private GUI gui;

    public ToggleListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.toggleToolBar();
    }
}
