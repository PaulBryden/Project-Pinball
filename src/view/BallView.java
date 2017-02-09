package view;

import model.ICircle;

import java.awt.*;

public class BallView extends CircleView{
    public BallView(ICircle gizmo) {
        super(gizmo);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.fillOval((50 - (10/2)), (90 - (10/2)),10 , 10);
    }
}
