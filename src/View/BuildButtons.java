package View;

import javax.swing.JButton;
import javax.swing.JToolBar;

class BuildButtons extends JToolBar{
    BuildButtons(){
        super("Build Mode");
        JButton ballBtn = new JButton("Ball");
        JButton AbsorberBtn = new JButton("Absorber");
        JButton circleBtn = new JButton("Circle");
        JButton lineBtn = new JButton("Line");
        JButton squareBtn = new JButton("Square");
        JButton triangleBtn = new JButton("Triangle");
        JButton lFlipperBtn = new JButton("L.Flipper");
        JButton rFlipperBtn = new JButton("R.Flipper");

        this.setFloatable(false);
        this.setRollover(true);

        this.add(ballBtn);
        this.add(AbsorberBtn);
        this.add(circleBtn);
        this.add(lineBtn);
        this.add(squareBtn);
        this.add(triangleBtn);
        this.add(lFlipperBtn);
        this.add(rFlipperBtn);
    }
}
