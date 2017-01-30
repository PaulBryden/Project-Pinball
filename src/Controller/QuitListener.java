package Controller;

import View.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitListener implements ActionListener{
    private GUI gui;

    public QuitListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
