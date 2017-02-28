package tests;

import org.junit.Test;

import model.Absorber;
import model.BallGizmo;
import model.CircleGizmo;
import model.CollisionDetails;
import model.GameModel;
import model.IBall;
import model.LeftFlipper;
import model.RightFlipper;
import model.SquareGizmo;
import model.TriangleGizmo;
import model.Wall;
import physics.Vect;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;

public class CollisionTest {
	
	@Before
	public void setup(){
		
	}
	@Test
	public void PhysicsEvaluationSingleWall() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ball = new BallGizmo("B1",topLeft, new Vect(40,0));
		Wall wall = new Wall(new Vect(3,1), new Vect(3,9));
		GameModel gameModel = new GameModel();
		gameModel.addBall(ball);
		gameModel.addGizmo(wall);
		CollisionDetails newCollisions=gameModel.evaluateCollisions();
		
		assert(newCollisions.getBall().equals(ball));
	}
	
	@Test
	public void PhysicsEvaluationSingleSquare() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ball = new BallGizmo("B1",topLeft, new Vect(40,0));
		SquareGizmo gizmo = new SquareGizmo("S1",new Vect(3,1));
		GameModel gameModel = new GameModel();
		gameModel.addBall(ball);
		gameModel.addGizmo(gizmo);
		CollisionDetails newCollisions=gameModel.evaluateCollisions();
		
		assert(newCollisions.getBall().equals(ball));
	}
	
	@Test
	public void PhysicsEvaluationSingleCircle() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ball = new BallGizmo("B1",topLeft, new Vect(40,0));
		CircleGizmo gizmo = new CircleGizmo("C1",new Vect(3,1));
		GameModel gameModel = new GameModel();
		gameModel.addBall(ball);
		gameModel.addGizmo(gizmo);
		CollisionDetails newCollisions=gameModel.evaluateCollisions();
		
		assert(newCollisions.getBall().equals(ball));
	}
	
	@Test
	public void PhysicsEvaluationSingleFlipper() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ball = new BallGizmo("B1",topLeft, new Vect(40,0));
		LeftFlipper gizmo = new LeftFlipper("LF1",new Vect(3,1));
		GameModel gameModel = new GameModel();
		gameModel.addBall(ball);
		gameModel.addGizmo(gizmo);
		CollisionDetails newCollisions=gameModel.evaluateCollisions();
		
		assert(newCollisions.getBall().equals(ball));
	}
	
	@Test
	public void PhysicsEvaluation2BallCollisionFlipper() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ballFast = new BallGizmo("B1",topLeft, new Vect(40,0));
		BallGizmo ballSlow = new BallGizmo("B2",topLeft, new Vect(10,0));
		LeftFlipper gizmo = new LeftFlipper("LF1",new Vect(3,1));
		GameModel gameModel = new GameModel();
		gameModel.addBall(ballFast);
		gameModel.addBall(ballSlow);
		gameModel.addGizmo(gizmo);
		CollisionDetails newCollisions=gameModel.evaluateCollisions();
		
		assert(newCollisions.getBall().equals(ballFast));
	}
	
	@Test
	public void PhysicsEvaluation2BallCollisionAbsorber() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ballFast = new BallGizmo("B1",topLeft, new Vect(40,0));
		BallGizmo ballSlow = new BallGizmo("B2",topLeft, new Vect(10,0));
		GameModel gameModel = new GameModel();

		Absorber gizmo = new Absorber("LF1",new Vect(3,1),new Vect(4,2),gameModel.getBalls());
		gameModel.addBall(ballFast);
		gameModel.addBall(ballSlow);
		gameModel.addGizmo(gizmo);
		CollisionDetails newCollisions=gameModel.evaluateCollisions();
		
		assert(newCollisions.getBall().equals(ballFast));
	}
	
	@Test
	public void AbsorberBallAbsorbTest() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ballFast = new BallGizmo("B1",topLeft, new Vect(40,0));
		GameModel gameModel = new GameModel();
		Absorber gizmo = new Absorber("LF1",new Vect(3,1),new Vect(4,2),gameModel.getBalls());
		gameModel.addBall(ballFast);
		gameModel.addGizmo(gizmo);
		gizmo.absorbBall(ballFast);
		
		assert(gameModel.getBalls().size()==0);
	}
	
	@After
	public void teardown(){
		
	}
}
