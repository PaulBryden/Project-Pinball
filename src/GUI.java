import javax.swing.JFrame;
import javax.swing.WindowConstants;
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
        this.setLayout(new GridBagLayout());
        this.add(runButtons);
        this.add(menuBar);
        this.add(gameView);
        this.setTitle("Gizmo Ball");
        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.pack();
    }
}
