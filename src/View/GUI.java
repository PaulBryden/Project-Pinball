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

    public GUI(){
        super();
        menuBar = new MenuBar(this);
        toolbar = new RunToolBar();
        gameView = new GameView();
    }

    public void build(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridy = 0;

        setLayout(new GridBagLayout());
        setTitle("Gizmo Ball");
        setJMenuBar(menuBar);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(650, 473);

        add(toolbar, constraints);
        constraints.gridy = 1;
        add(gameView, constraints);
    }

    /**
     * Went for redrawing/redefining method rather than making one toolbar invisible and the other visible
     * as redrawing the entire frame could help later when this is expanded to show a Build game view on view toggle
     * (with grid squares)
     */
    public void toggleView(){
        remove(toolbar);
        if(toolbar instanceof RunToolBar){
            toolbar = new BuildToolBar();
        } else {
            toolbar = new RunToolBar();
        }
        add(toolbar);
        revalidate();
        repaint();
    }
}
