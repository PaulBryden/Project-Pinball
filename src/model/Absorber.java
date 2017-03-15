package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class Absorber extends AbstractGizmo {

	private AbsorbAction absorb;
	List<IBall> storedBalls;
	Vect bottomRightCoords;
	private int ballsToFire;
	private IModel model;

	public Absorber(IModel model, String id, Vect topLeftCoords, Vect bottomRightCoords, List<IBall> storedBalls) {
		super(id, topLeftCoords, (int) (bottomRightCoords.x() - topLeftCoords.x()), (int) (bottomRightCoords.y() - topLeftCoords.y()), Constants.ABSORBER_DEFAULT_COLOUR, true);
		this.model = model;
		this.bottomRightCoords = bottomRightCoords;
		this.absorb = new AbsorbAction(this);
		this.addTriggerAction(new AbsorberFireAction(this));
		generateLinesAndCircles();
		this.coefficientOfReflection = 0;
		this.storedBalls = storedBalls;
		this.ballsToFire = 0;
	}

	public Absorber(IModel model, String id, int x1, int y1, int x2, int y2, List<IBall> balls) {
		this(model, id, new Vect(x1, y1), new Vect(x2, y2), balls);
	}

	public Absorber(IModel model, String id, int x1, int y1, int x2, int y2) {
		this(model, id, x1, y1, x2, y2, new LinkedList<>());
	}

	public Absorber(IModel model, String id, Vect topLeftCoords, Vect bottomRightCoords) {
		this(model, id, topLeftCoords, bottomRightCoords, new LinkedList<>());
	}

	@Override
	protected void generateLinesAndCircles() {
		circles.clear();
		circles.add(new Circle(coords.x(), coords.y(), 0));
		circles.add(new Circle(bottomRightCoords.x(), coords.y(), 0));
		circles.add(new Circle(coords.x(), bottomRightCoords.y(), 0));
		circles.add(new Circle(bottomRightCoords.x(), bottomRightCoords.y(), 0));
		lines.clear();
		lines.add(new LineSegment(coords.x(), coords.y(), bottomRightCoords.x(), coords.y()));
		lines.add(new LineSegment(coords.x(), bottomRightCoords.y(), bottomRightCoords.x(), bottomRightCoords.y()));
		lines.add(new LineSegment(coords.x(), coords.y(), coords.x(), bottomRightCoords.y()));
		lines.add(new LineSegment(bottomRightCoords.x(), coords.y(), bottomRightCoords.x(), bottomRightCoords.y()));
	}

	@Override
	public void onCollision(IBall ball) {
		absorb.performAction(ball);
	}

	@Override
	public List<Vect> getExactCoords() {
		List<Vect> coordVector = new ArrayList<Vect>();
		coordVector.add(this.getAllLineSegments().get(0).p1());
		coordVector.add(this.getAllLineSegments().get(0).p2());
		coordVector.add(this.getAllLineSegments().get(1).p2());
		coordVector.add(this.getAllLineSegments().get(2).p2());
		return coordVector;
	}

	@Override
	public void setGridCoords(Vect coords) {
		int xdiff = (int) (coords.x() - this.coords.x());
		int ydiff = (int) (coords.y() - this.coords.y());
		this.coords = coords;
		this.bottomRightCoords = new Vect(bottomRightCoords.x() + xdiff, bottomRightCoords.y() + ydiff);
		generateLinesAndCircles();
	}
	
	public IBall getNextBall() {
		if (this.storedBalls.isEmpty())
			return null;
		return this.storedBalls.get(0);
	}

	public void fireBall() {
		if (storedBalls.size() > ballsToFire) {
			ballsToFire++;
			updateFiring();
		}
	}
	
	public void updateFiring() {
		if (ballsToFire > 0 && hasSpaceToFire()) {
			IBall ball = storedBalls.remove(0);
			ball.setVelo(new Vect(0, -50));
			model.addBall(ball);
			ball.setCentre(new Vect(bottomRightCoords.x() - ball.getRadius()-0.05, coords.y() - ball.getRadius()));
			ballsToFire--;
		}
	}

	public void absorbBall(IBall ball) {
		storedBalls.add(ball);
		model.removeBall(ball);
	}

	public Vect getBottomRightCoords() {
		return bottomRightCoords;
	}

	@Override
	public String serializeGizmo() {
		String serializedGizmo = "Absorber " + getID() + " " + (int) this.getGridCoords().x() + " "
				+ (int) this.getGridCoords().y() + " " + (int) this.getBottomRightCoords().x() + " "
				+ (int) this.getBottomRightCoords().y() + "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + this.getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

	/**
	 * 
	 * @return If there is enough space above the absorber to fire a ball
	 */
	private boolean hasSpaceToFire() {
		Vect firingCell = new Vect(getGridCoords().x() + gridWidth - 1, getGridCoords().y() - 1);
		return model.isCellEmpty(firingCell);
	}
}
