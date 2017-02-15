package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

import controller.LoadBoardListener;
import controller.QuitListener;
import controller.ModeToggleListener;
import controller.SaveBoardListener;

class MenuBar extends JMenuBar {

    MenuBar(MainWindow mainWindow){
        super();
        JMenu menu = new JMenu();
        JMenuItem menuItem = new JMenuItem("Save", KeyEvent.VK_S);
        menuItem.addActionListener(new SaveBoardListener(mainWindow));
        menu.add(menuItem);

        menuItem = new JMenuItem("Load", KeyEvent.VK_L);
        menuItem.addActionListener(new LoadBoardListener());
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Toggle Mode", KeyEvent.VK_T);
        menuItem.addActionListener(new ModeToggleListener(mainWindow));
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        menuItem.addActionListener(new QuitListener());
        menu.add(menuItem);

        menu.setText("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        add(menu);
    }
}
