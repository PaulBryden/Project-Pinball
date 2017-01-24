import javax.swing.*;
import java.awt.*;

class ButtonPanel extends JPanel{
    ButtonPanel(){
        JButton runBtn = new JButton("Run");
        JButton pauseBtn = new JButton("Pause");
        JButton stopBtn = new JButton("Stop");
        JButton tickBtn = new JButton("Tick");

        this.setLayout(new GridBagLayout());
        Dimension btnSize = new Dimension(70, 40);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        constraints.gridy = 0;
        runBtn.setPreferredSize(btnSize);
        this.add(runBtn, constraints);

        constraints.gridy = 1;
        pauseBtn.setPreferredSize(btnSize);
        this.add(pauseBtn, constraints);

        constraints.gridy = 2;
        stopBtn.setPreferredSize(btnSize);
        this.add(stopBtn, constraints);

        constraints.gridy = 3;
        tickBtn.setPreferredSize(btnSize);
        this.add(tickBtn, constraints);

    }
}
