package View;

import javax.swing.JButton;
import javax.swing.JToolBar;

class BuildToolBar extends JToolBar{
    BuildToolBar(){
        //TODO: Move Gizmoz to side panel, replace the ones here with 'Add'
        super("Build Mode");
        JButton ballBtn = new JButton("Ball");
        JButton absorberBtn = new JButton("Absorber");
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
        add(absorberBtn);
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
