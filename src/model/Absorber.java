package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class Absorber extends AbstractGizmo {
	
	private AbsorbAction absorb;
	List<IBall> allBalls;
	List<IBall> storedBalls;
	Vect bottomRightCoords;
	public Absorber(String id, Vect topLeftCoords, Vect bottomRightCoords,  List<IBall> balls) {
		super(id, topLeftCoords, Constants.ABSORBER_DEFAULT_COLOUR, true);
		this.bottomRightCoords=bottomRightCoords;
		this.absorb = new AbsorbAction(this);
		this.addTriggerAction(new AbsorberFireAction(this));
		generateLinesAndCircles();
		this.coefficientOfReflection = 0;
		this.allBalls = balls;
		this.storedBalls = new LinkedList<>();
	}

	public Absorber(String id, int x1, int y1, int x2, int y2, List<IBall> balls) {
		this(id, new Vect(x1, y1), new Vect(x2,y2), balls);
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
		// TODO Auto-generated method stub
		List<Vect> coordVector = new ArrayList<Vect>();
		coordVector.add(this.getAllLineSegments().get(0).p1());
		coordVector.add(this.getAllLineSegments().get(0).p2());
		coordVector.add(this.getAllLineSegments().get(1).p2());
		coordVector.add(this.getAllLineSegments().get(2).p2());

		return coordVector;
		
	}
	
	@Override
	public void setGridCoords(Vect coords) {
		int xdiff=(int) (coords.x()-this.coords.x());
		int ydiff=(int) (coords.y()-this.coords.y());
		this.coords = coords;
		this.bottomRightCoords = new Vect(bottomRightCoords.x()+xdiff, bottomRightCoords.y()+ydiff);
		generateLinesAndCircles();
	}

	public void fireBall() {
		if (!storedBalls.isEmpty()) {
			IBall ball = storedBalls.remove(0);
			ball.setVelo(new Vect(0, -50));
			ball.setGridCoords(new Vect(bottomRightCoords.x() - 1 + ball.getRadius(), bottomRightCoords.y() - 2 - ball.getRadius()));
			allBalls.add(ball);
		}
	}
	
	public void absorbBall(IBall ball) {
		if (storedBalls.size() > 0) {
			allBalls.add(storedBalls.get(0));
			storedBalls.remove(0);
			storedBalls.add(ball);
			ball.setVelo(new Vect(0, -50));
		} else {
			storedBalls.add(ball);
			allBalls.remove(ball);
		}
	}
	
	public void addBall(IBall ball) {
		allBalls.add(ball);
	}
	
	public Vect getBottomRightCoords() {
		return bottomRightCoords;
	}
	
	@Override
	public String serializeGizmo() {
		String serializedGizmo = "Absorber " + getID() + " " + this.getGridCoords().x() + " " + this.getGridCoords().y() + " "
				+ this.getBottomRightCoords().x() + " " + this.getBottomRightCoords().y() + " " + "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + this.getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}
}
