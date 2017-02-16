package view;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import model.IModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class MainWindow extends JFrame{
    private IModel model;
    private MenuBar menuBar;
    private JToolBar toolbar;
    private JToolBar sideToolBar;
    private Board board;
    private GridBagConstraints constraints;

    public MainWindow(IModel model){
        super();
        this.model = model;
        board = new Board(this.model);
        menuBar = new MenuBar(this);
        sideToolBar = new JToolBar();
        toolbar = new BuildToolBar(this, board);
        constraints = new GridBagConstraints();
    }

    public void build(){
        setLayout(new GridBagLayout());
        setTitle("Gizmo Ball");
        setJMenuBar(menuBar);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 550);

        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(toolbar, constraints);

        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(board, constraints);

        setVisible(true);
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

    public void toggleView(){
        constraints.fill = GridBagConstraints.VERTICAL;

        remove(toolbar);

        if(toolbar instanceof RunToolBar){
            ((RunToolBar) toolbar).stop();
            toolbar = new BuildToolBar(this, board);
        } else {
            toolbar = new RunToolBar(model);
            remove(sideToolBar);
            sideToolBar = new JToolBar();
        }

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(toolbar, constraints);

        revalidate();
        repaint();
    }

    public Board getBoard(){
        return (board);
    }
}
