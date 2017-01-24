import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

class GUI {
    private JFrame frame;
    private MenuBar menuBar;
    private JPanel panel;
    private JButton runBtn;
    private JButton pauseBtn;
    private JButton stopBtn;
    private JButton tickBtn;

    GUI(){
        frame = new JFrame();
        menuBar = new MenuBar();
        panel = new JPanel(new GridBagLayout());
        runBtn = new JButton("Run");
        pauseBtn = new JButton("Pause");
        stopBtn = new JButton("Stop");
        tickBtn = new JButton("Tick");
    }

    void build(){
        //Build button panel
        Dimension btnSize = new Dimension(70, 40);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        constraints.gridy = 0;
        runBtn.setPreferredSize(btnSize);
        panel.add(runBtn, constraints);

        constraints.gridy = 1;
        pauseBtn.setPreferredSize(btnSize);
        panel.add(pauseBtn, constraints);

        constraints.gridy = 2;
        stopBtn.setPreferredSize(btnSize);
        panel.add(stopBtn, constraints);

        constraints.gridy = 3;
        tickBtn.setPreferredSize(btnSize);
        panel.add(tickBtn, constraints);

        //Build frame
        frame.setLayout(new GridBagLayout());
        constraints.gridy = 0;
        frame.add(panel, constraints);
        frame.add(menuBar);
        frame.setTitle("Gizmo Ball");
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 600);
    }
}
