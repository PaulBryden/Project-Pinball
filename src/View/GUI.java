package View;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GUI extends JFrame{
    private MenuBar menuBar;
    private JToolBar toolbar;
    private GameView gameView;

    GUI(){
        super();
        menuBar = new MenuBar(this);
        toolbar = new RunButtons();
        gameView = new GameView();
    }

    void build(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridy = 0;
        setLayout(new GridBagLayout());
        setTitle("Gizmo Ball");
        setJMenuBar(menuBar);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(toolbar, constraints);
        constraints.gridy = 1;
        add(gameView, constraints);
        pack();
    }

    public void toggleToolBar(){
        if(toolbar instanceof RunButtons){
            toolbar = new BuildButtons();
        } else {
            toolbar = new RunButtons();
        }
        toolbar.invalidate();
    }
}
