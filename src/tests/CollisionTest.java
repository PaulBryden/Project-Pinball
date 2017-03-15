package tests;

import org.junit.Test;

import model.BallGizmo;
import model.CircleGizmo;
import model.CollisionDetails;
import model.CollisionEvaluator;
import model.GizmoFactory;
import model.IAbsorber;
import model.IBall;
import model.IModel;
import model.LeftFlipper;
import model.ModelFactory;
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
	//SIngle Ball Single Wall Collision Evaluation
	public void PhysicsEvaluationSingleWall() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ball = new BallGizmo("B1",topLeft, new Vect(40,0));
		Wall wall = new Wall(new Vect(1.6,1), new Vect(1.6,9));
        IModel model = ModelFactory.getModel();
		model.addBall(ball);
		model.addGizmo(wall);
		CollisionEvaluator ce = new CollisionEvaluator(model);
		ce.evaluate();
		CollisionDetails newCollisions=ce.getCollision();
		assertEquals(newCollisions.getBall(),ball);
	}
	
	@Test
	//Single Ball Single Square Collision Evaluation
	public void PhysicsEvaluationSingleSquare() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ball = new BallGizmo("B1",topLeft, new Vect(100,0));
		SquareGizmo gizmo = new SquareGizmo("S1",new Vect(2,1));
        IModel model = ModelFactory.getModel();
		model.addBall(ball);
		model.addGizmo(gizmo);
		CollisionEvaluator ce = new CollisionEvaluator(model);
		ce.evaluate();
		CollisionDetails newCollisions=ce.getCollision();
		assertEquals(newCollisions.getBall(),ball);
	}
	
	@Test
	//Single Ball Single Circle Collision Evaluation
	public void PhysicsEvaluationSingleCircle() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ball = new BallGizmo("B1",topLeft, new Vect(4000,0));
		CircleGizmo gizmo = new CircleGizmo("C1",new Vect(3,1));
        IModel model = ModelFactory.getModel();
		model.addBall(ball);
		model.addGizmo(gizmo);
		CollisionEvaluator ce = new CollisionEvaluator(model);
		ce.evaluate();
		CollisionDetails newCollisions=ce.getCollision();
		assertEquals(newCollisions.getBall(),ball);
	}
	
	@Test
	//Single Ball Single Flipper Collision Evaluation
	public void PhysicsEvaluationSingleFlipper() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ball = new BallGizmo("B1",topLeft, new Vect(4000,0));
		LeftFlipper gizmo = new LeftFlipper("LF1",new Vect(3,1));
        IModel model = ModelFactory.getModel();
		model.addBall(ball);
		model.addGizmo(gizmo);
		CollisionEvaluator ce = new CollisionEvaluator(model);
		ce.evaluate();
		CollisionDetails newCollisions=ce.getCollision();
		assertEquals(newCollisions.getBall(),ball);
	}
	
	@Test

	//Ensures the flipper collision is handled correctly, if 2 balls are destined to collide with it.
	public void PhysicsEvaluation2BallCollisionFlipper() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ballFast = new BallGizmo("B1",topLeft, new Vect(4000,0));
		BallGizmo ballSlow = new BallGizmo("B2",topLeft, new Vect(1000,0));
		LeftFlipper gizmo = new LeftFlipper("LF1",new Vect(3,1));
        IModel model = ModelFactory.getModel();
		model.addBall(ballFast);
		model.addBall(ballSlow);
		model.addGizmo(gizmo);
		CollisionEvaluator ce = new CollisionEvaluator(model);
		ce.evaluate();
		CollisionDetails newCollisions=ce.getCollision();
		assertEquals(newCollisions.getBall(),ballFast);
	}
	
	@Test
	//Ensures the absorber collision is handled correctly, if 2 balls are destined to collide with it.
	public void PhysicsEvaluation2BallCollisionAbsorber() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ballFast = new BallGizmo("B1",topLeft, new Vect(4000,0));
		BallGizmo ballSlow = new BallGizmo("B2",topLeft, new Vect(1000,0));
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IAbsorber gizmo = gf.getAbsorber(new Vect(3,1),new Vect(4,2));
		model.addBall(ballFast);
		model.addBall(ballSlow);
		model.addGizmo(gizmo);
		CollisionEvaluator ce = new CollisionEvaluator(model);
		ce.evaluate();
		CollisionDetails newCollisions=ce.getCollision();
		assertEquals(newCollisions.getBall(),ballFast);
	}
	
	
	@Test
	//Checks the collision is being handled, and the resultant post-collision velocities are being correctly set.
	public void BallOnBallCollision() {
		Vect topLeft = new Vect(1,1);
		Vect topLeft2 = new Vect(3,1);
		BallGizmo ballFast = new BallGizmo("B1",topLeft, new Vect(4000,0));
		BallGizmo ballSlow = new BallGizmo("B2",topLeft2, new Vect(-1000,0));
        IModel model = ModelFactory.getModel();
		model.addBall(ballFast);
		model.addBall(ballSlow);
		CollisionEvaluator ce = new CollisionEvaluator(model);
		ce.evaluate();
		CollisionDetails newCollisions=ce.getCollision();
		assertTrue(((newCollisions.getBall().equals(ballFast))&&newCollisions.getOtherBallVelo().x()>ballSlow.getVelo().x())||((newCollisions.getBall().equals(ballSlow))&&newCollisions.getOtherBallVelo().x()<ballFast.getVelo().x()));
	}
	
	@Test
	//Ensures Balls are correctly absorbed on absorber collision.
	public void AbsorberBallAbsorbTest() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		IBall ballFast = new BallGizmo("B1",topLeft, new Vect(4000,0));
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IAbsorber gizmo = gf.getAbsorber(new Vect(3,1),new Vect(4,2));
		model.addBall(ballFast);
		model.addGizmo(gizmo);
		gizmo.absorbBall(ballFast);
		assertTrue(model.getBalls().size()==0);
	}
	
	@Test
	//Absorber trigger correct operation.
	public void AbsorberBallAbsorbTriggerTest() {
		Vect topLeft = new Vect(1,1);
		Vect wallTop = new Vect (3,1);
		BallGizmo ballFast = new BallGizmo("B1",topLeft, new Vect(4000,0));
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IAbsorber gizmo = gf.getAbsorber(new Vect(3,1),new Vect(4,2));
		model.addBall(ballFast);
		model.addGizmo(gizmo);
		gizmo.absorbBall(ballFast);
		gizmo.performActions();
		assertTrue(model.getBalls().size()==1);
	}
	
	@After
	public void teardown(){
		
	}
}
