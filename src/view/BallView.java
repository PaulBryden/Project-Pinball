package view;

import model.ICircle;

import java.awt.*;

public class BallView extends CircleView {
    private static final int RADIUS = 10;
    private static final int GRID_WIDTH = 20;

    public BallView(ICircle gizmo) {
        super(gizmo);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.fillOval((((int) gizmo.getGridCoords().x() * GRID_WIDTH) - (RADIUS/2)),
                (((int) gizmo.getGridCoords().y() * GRID_WIDTH) - (RADIUS/2)), RADIUS, RADIUS);
    }
}
