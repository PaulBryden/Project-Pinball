import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

class MenuBar extends JMenuBar {
    MenuBar(){
        JMenu menu = new JMenu();
        JMenuItem menuItem = new JMenuItem("Save", KeyEvent.VK_S);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load", KeyEvent.VK_L);
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Toggle Mode", KeyEvent.VK_T);
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        menu.add(menuItem);

        menu.setText("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        this.add(menu);
    }
}
