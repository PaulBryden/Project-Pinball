import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

class GUI extends JFrame{
    private MenuBar menuBar;
    private RunButtons runButtons;
    private GameView gameView;

    GUI(){
        super();
        menuBar = new MenuBar();
        runButtons = new RunButtons();
        gameView = new GameView();
    }

    void build(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridy = 0;
        this.setLayout(new GridBagLayout());
        this.setTitle("Gizmo Ball");
        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.add(runButtons, constraints);
        constraints.gridy = 1;
        this.add(gameView, constraints);
        this.pack();
    }
}
