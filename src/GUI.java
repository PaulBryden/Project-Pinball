import javax.swing.*;
import java.awt.event.KeyEvent;

class GUI {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;

    GUI(){
        frame = new JFrame();
        menuBar = new JMenuBar();
        menu = new JMenu();
    }

    void build(){
        //Build menu
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
        menuBar.add(menu);
        frame.add(menuBar);

        //Build frame
        frame.setTitle("Gizmo Ball");
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 600);
    }
}
