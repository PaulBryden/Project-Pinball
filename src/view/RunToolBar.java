package view;

import controller.RunListener;
import controller.KeyListener;
import model.IModel;

import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;

class RunToolBar extends JToolBar{
    private JButton stopBtn;

    RunToolBar(IModel model){
        super("Run Mode");
        JButton runBtn = new JButton("Run");
        stopBtn = new JButton("Stop");
        JButton tickBtn = new JButton("Tick");
        ActionListener runListener = new RunListener(model);

        setFloatable(false);
        setRollover(true);

        runBtn.addActionListener(runListener);
        runBtn.addKeyListener(new KeyListener(model));
        add(runBtn);

        stopBtn.addActionListener(runListener);
        add(stopBtn);

        addSeparator();

        tickBtn.addActionListener(runListener);
        add(tickBtn);
    }

    public void stop(){
        stopBtn.doClick();
    }
}
