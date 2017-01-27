package View;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

import Controller.ToggleListener;

class MenuBar extends JMenuBar {

    MenuBar(GUI gui){
        super();
        JMenu menu = new JMenu();
        JMenuItem menuItem = new JMenuItem("Save", KeyEvent.VK_S);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load", KeyEvent.VK_L);
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Toggle Mode", KeyEvent.VK_T);
        menuItem.addActionListener(new ToggleListener(gui));
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        menu.add(menuItem);

        menu.setText("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        add(menu);
    }
}
