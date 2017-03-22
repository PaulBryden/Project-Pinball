package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import controller.PrimaryActionListener;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 2834492556483662470L;
	private PrimaryActionListener listener;
	
	private Map<String, JMenuItem> menuItems;
	
    MenuBar(PrimaryActionListener listener) {
        super();
        menuItems = new HashMap<>();
        this.listener = listener;
        listener.addMenuBar(this);
        /************** FILE ****************/
        JMenu menu = new JMenu("File");
        addMenuItem(menu, "Save...", "save", KeyEvent.VK_S);
        addMenuItem(menu, "Load...", "load", KeyEvent.VK_L);
        menu.addSeparator();
        addMenuItem(menu, "Toggle Mode", "toggle", KeyEvent.VK_T);
        menu.addSeparator();
        addMenuItem(menu, "Quit", "quit", KeyEvent.VK_Q);
        menu.setMnemonic(KeyEvent.VK_M);
        add(menu);
        /************** NETWORK ****************/
        menu = new JMenu("Network");
        addMenuItem(menu, "Host...", "host", KeyEvent.VK_H);
        addMenuItem(menu, "Connect...", "client", KeyEvent.VK_C);
        addMenuItem(menu, "Disconnect", "disconnect", KeyEvent.VK_D);
        disableItem("disconnect");
        add(menu);
        /************** HELP ****************/
        menu = new JMenu("?");
        addMenuItem(menu, "About", "about", KeyEvent.VK_A);
        add(menu);
    }
    
    private void addMenuItem(JMenu menu, String name, String action, int mnemonic) {
    	JMenuItem item = new JMenuItem(name, mnemonic);
    	item.setActionCommand(action);
    	item.addActionListener(listener);
    	menuItems.put(action, item);
    	menu.add(item);
    }

	public void enableItem(String name) {
		JMenuItem item = menuItems.get(name);
		if (item != null) {
			item.setEnabled(true);
		}
	}

	public void disableItem(String name) {
		JMenuItem item = menuItems.get(name);
		if (item != null) {
			item.setEnabled(false);
		}
	}
}
