package view;

import model.ICircle;

import java.awt.*;

public class BallView extends CircleView{
    public BallView(ICircle gizmo) {
        super(gizmo);
    }

    @Override
    public void paint(Graphics g) {
        g.fillOval((50 - (10/2)), (90 - (10/2)),10 , 10);
    }
}
