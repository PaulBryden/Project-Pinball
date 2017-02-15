package view;

import controller.TickListener;
import model.IModel;

import javax.swing.JButton;
import javax.swing.JToolBar;

class RunToolBar extends JToolBar{
    RunToolBar(IModel model){
        super("Run Mode");
        JButton runBtn = new JButton("Run");
        JButton pauseBtn = new JButton("Pause");
        JButton stopBtn = new JButton("Stop");
        JButton tickBtn = new JButton("Tick");

        setFloatable(false);
        setRollover(true);

        add(runBtn);
        add(pauseBtn);
        add(stopBtn);
        addSeparator();
        tickBtn.addActionListener(new TickListener(model));
        add(tickBtn);
    }
}
