package model;

import java.awt.Color;
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

	public Absorber(String id, Vect coords, List<IBall> balls) {
		super(id, coords, Color.GREEN, true);
		this.absorb = new AbsorbAction(this);
		this.addTriggerAction(new AbsorberFireAction(this));
		generateLinesAndCircles();
		this.coefficientOfReflection = 0;
		this.allBalls = balls;
		this.storedBalls = new LinkedList<>();
	}

	public Absorber(String id, int x, int y,List<IBall> balls) {
		this(id, new Vect(x, y), balls);
	}

	@Override
	protected void generateLinesAndCircles() {
		circles.clear();
		circles.add(new Circle(coords.x(), coords.y(), 0));
		circles.add(new Circle(coords.x() + 1, coords.y(), 0));
		circles.add(new Circle(coords.x(), coords.y() + 1, 0));
		circles.add(new Circle(coords.x() + 1, coords.y() + 1, 0));
		lines.clear();
		lines.add(new LineSegment(coords.x(), coords.y(), coords.x() + 1, coords.y()));
		lines.add(new LineSegment(coords.x(), coords.y() + 1, coords.x() + 1, coords.y() + 1));
		lines.add(new LineSegment(coords.x(), coords.y(), coords.x(), coords.y() + 1));
		lines.add(new LineSegment(coords.x() + 1, coords.y(), coords.x() + 1, coords.y() + 1));
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

	public void fireBall() {
		if (!storedBalls.isEmpty()) {
			IBall ball = storedBalls.remove(0);
			ball.setVelo(new Vect(0, -50));
			ball.setGridCoords(new Vect(coords.x() + 1 - ball.getRadius(), coords.y() - ball.getRadius()));
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
}
