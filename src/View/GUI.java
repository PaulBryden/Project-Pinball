package View;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GUI extends JFrame{
    private MenuBar menuBar;
    private JToolBar toolbar;
    private JToolBar sideToolBar;
    private GameView gameView;

    public GUI(){
        super();
        menuBar = new MenuBar(this);
        toolbar = new BuildToolBar(this);
        sideToolBar = new JToolBar();
        gameView = new GameView();
    }

    public void build(){
        setLayout(new GridBagLayout());
        setTitle("Gizmo Ball");
        setJMenuBar(menuBar);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(650, 473);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(toolbar, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(gameView, constraints);
    }

    public void addSideToolBar(JToolBar sideToolBar){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 1;

        this.sideToolBar = sideToolBar;
        add(this.sideToolBar, constraints);

        revalidate();
        repaint();
    }

    public JToolBar getSideToolBar(){
        return (sideToolBar);
    }

    /**
     * Went for redrawing/redefining method rather than making one toolbar invisible and the other visible
     * as redrawing the entire frame could help later when this is expanded to show a Build game view on view toggle
     * (with grid squares)
     */
    public void toggleView(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        remove(toolbar);

        if(toolbar instanceof RunToolBar){
            toolbar = new BuildToolBar(this);
        } else {
            toolbar = new RunToolBar();
            remove(sideToolBar);
            sideToolBar = new JToolBar();
        }

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(toolbar, constraints);

        revalidate();
        repaint();
    }
}
