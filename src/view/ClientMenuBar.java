package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

import controller.ClientBoardListener;
import controller.ClientDisconnectListener;
import controller.HostBoardListener;
import controller.LoadBoardListener;
import controller.QuitListener;
import controller.ModeToggleListener;
import controller.SaveBoardListener;

class ClientMenuBar extends JMenuBar {

    ClientMenuBar(MainWindow mainWindow){
        super();
        JMenu menu = new JMenu();
        
        
        JMenuItem menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        menuItem.addActionListener(new QuitListener());
        menu.add(menuItem);

        menu.addSeparator();
        
        menuItem = new JMenuItem("Disconnect", KeyEvent.VK_Q);
        menuItem.addActionListener(new ClientDisconnectListener(mainWindow));
        menu.add(menuItem);
        
        menu.setText("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        add(menu);
    }
}
