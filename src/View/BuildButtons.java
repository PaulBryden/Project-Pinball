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
        JButton removeBtn = new JButton("Remove");
        JButton rotateBtn = new JButton("Rotate");
        JButton moveBtn = new JButton("Move");
        JButton connectBtn = new JButton("Connect");

        setFloatable(false);
        setRollover(true);

        add(ballBtn);
        add(AbsorberBtn);
        add(circleBtn);
        add(lineBtn);
        add(squareBtn);
        add(triangleBtn);
        add(lFlipperBtn);
        add(rFlipperBtn);

        addSeparator();

        add(removeBtn);
        add(rotateBtn);
        add(moveBtn);
        add(connectBtn);
    }
}
