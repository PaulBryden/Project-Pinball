import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.*;

class GUI {
    private JFrame frame;
    private MenuBar menuBar;
    private ButtonPanel buttonPanel;
    private GameView gameView;

    GUI(){
        frame = new JFrame();
        menuBar = new MenuBar();
        buttonPanel = new ButtonPanel();
        gameView = new GameView();
    }

    void build(){
        frame.setLayout(new GridLayout());
        frame.add(buttonPanel);
        frame.add(menuBar);
        frame.add(gameView);
        frame.setTitle("Gizmo Ball");
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 450);
    }
}
