package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

import controller.PrimaryActionListener;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 2834492556483662470L;
	private PrimaryActionListener listener;
	
    public MenuBar(MainWindow mainWindow, PrimaryActionListener listener) {
        super();
        this.listener = listener;
        listener.addMenuBar(this);
        /************** FILE ****************/
        JMenu menu = new JMenu("File");
        menu.add(getMenuItem("Save", "save", KeyEvent.VK_S));
        menu.add(getMenuItem("Load", "load", KeyEvent.VK_L));
        menu.addSeparator();
        menu.add(getMenuItem("Toggle Mode", "toggle", KeyEvent.VK_T));
        menu.addSeparator();
        menu.add(getMenuItem("Quit", "quit", KeyEvent.VK_Q));
        menu.addSeparator();
        menu.setMnemonic(KeyEvent.VK_M);
        add(menu);
        /************** NETWORK ****************/
        menu = new JMenu("Network");
        menu.add(getMenuItem("Host", "host", KeyEvent.VK_H));
        menu.add(getMenuItem("Connect", "client", KeyEvent.VK_C));
        menu.add(getMenuItem("Disconnect", "disconnect", KeyEvent.VK_D));
        add(menu);
        /************** HELP ****************/
        menu = new JMenu("?");
        menu.add(getMenuItem("About", "about", KeyEvent.VK_A));
        add(menu);
    }
    
    private JMenuItem getMenuItem(String name, String action, int mnemonic) {
    	JMenuItem item = new JMenuItem(name, mnemonic);
    	item.setActionCommand(action);
    	item.addActionListener(listener);
    	return item;
    }
}
