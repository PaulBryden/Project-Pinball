import javax.swing.*;

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
        menu.setText("Menu");
        menuBar.add(menu);
        frame.add(menuBar);

        frame.setTitle("Gizmo Ball");
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 600);
    }
}
