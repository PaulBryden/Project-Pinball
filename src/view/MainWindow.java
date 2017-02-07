package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    private MenuBar menuBar;
    private JToolBar toolbar;
    private JToolBar sideToolBar;
    private Board board;
    private JFileChooser fileManager;
    private GridBagConstraints constraints;

    public MainWindow(){
        super();
        menuBar = new MenuBar(this);
        toolbar = new BuildToolBar(this);
        sideToolBar = new JToolBar();
        board = new Board(null); /* TODO: Get Gizmos */
        fileManager = new JFileChooser();
        constraints = new GridBagConstraints();
    }

    public void build(){
        setLayout(new GridBagLayout());
        setTitle("Gizmo Ball");
        setJMenuBar(menuBar);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setSize(800, 550);

        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(toolbar, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(board, constraints);
    }

    public void addSideToolBar(JToolBar sideToolBar){
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 1;

        remove(this.sideToolBar);
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

    public void showSaveDialog(){
        fileManager.showSaveDialog(fileManager);
    }

    public void showLoadDialog(){
        fileManager.showOpenDialog(fileManager);
    }
}
