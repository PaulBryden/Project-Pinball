import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.GridBagLayout;

class GUI extends JFrame{
    private MenuBar menuBar;
    private ButtonPanel buttonPanel;
    private GameView gameView;

    GUI(){
        super();
        menuBar = new MenuBar();
        buttonPanel = new ButtonPanel();
        gameView = new GameView();
    }

    void build(){
        this.setLayout(new GridBagLayout());
        this.add(buttonPanel);
        this.add(menuBar);
        this.add(gameView);
        this.setTitle("Gizmo Ball");
        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
    }
}
