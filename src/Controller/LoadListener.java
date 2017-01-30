package Controller;

import View.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadListener implements ActionListener{
    private GUI gui;

    public LoadListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.showLoadDialog();
    }
}
