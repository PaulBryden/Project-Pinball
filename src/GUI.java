import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

class GUI {
    private JFrame frame;
    private MenuBar menuBar;
    private ButtonPanel buttonPanel;

    GUI(){
        frame = new JFrame();
        menuBar = new MenuBar();
        buttonPanel = new ButtonPanel();
    }

    void build(){
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        frame.add(buttonPanel, constraints);
        frame.add(menuBar);
        frame.setTitle("Gizmo Ball");
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 600);
    }
}
