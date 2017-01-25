package View;

import javax.swing.JButton;
import javax.swing.JToolBar;

class RunButtons extends JToolBar{
    RunButtons(){
        super("Run Mode");
        JButton runBtn = new JButton("Run");
        JButton pauseBtn = new JButton("Pause");
        JButton stopBtn = new JButton("Stop");
        JButton tickBtn = new JButton("Tick");

        this.setFloatable(false);
        this.setRollover(true);

        this.add(runBtn);
        this.add(pauseBtn);
        this.add(stopBtn);
        this.addSeparator();
        this.add(tickBtn);
    }
}
