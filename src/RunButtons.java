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

//        this.setPreferredSize(new Dimension(80, 380));

//        Dimension btnSize = new Dimension(80, 40);
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.fill = GridBagConstraints.VERTICAL;
//        constraints.insets = new Insets(3, 0, 3, 0);

//        constraints.gridy = 0;
//        runBtn.setPreferredSize(btnSize);
//        this.add(runBtn);
//
////        constraints.gridy = 1;
////        pauseBtn.setPreferredSize(btnSize);
//        this.add(pauseBtn);
//
////        constraints.gridy = 2;
////        stopBtn.setPreferredSize(btnSize);
//        this.add(stopBtn);
//
////        constraints.gridy = 3;
////        tickBtn.setPreferredSize(btnSize);
//        this.add(tickBtn);
    }
}
