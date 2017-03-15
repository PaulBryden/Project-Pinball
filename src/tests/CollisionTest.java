package tests;

import org.junit.Test;

import model.CollisionDetails;
import model.CollisionEvaluator;
import model.GizmoFactory;
import model.IAbsorber;
import model.IBall;
import model.IFlipper;
import model.IGizmo;
import model.IModel;
import model.ModelFactory;
import model.GizmoFactory.TYPE;
import physics.Vect;

import static org.junit.Assert.*;

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
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IBall ball = gf.getBall(topLeft, new Vect(-400,0));
		model.addBall(ball);
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
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IGizmo gizmo = gf.getGizmo(TYPE.Square, new Vect(2,1));
		IBall ball = gf.getBall(topLeft, new Vect(100,0));
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
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IGizmo gizmo = gf.getGizmo(TYPE.Circle, new Vect(3,1));
		IBall ball = gf.getBall(topLeft, new Vect(4000,0));
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
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IFlipper gizmo = (IFlipper) gf.getGizmo(TYPE.LeftFlipper, new Vect(3,1));
        IBall ball = gf.getBall(topLeft, new Vect(4000, 0));
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
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IFlipper gizmo = (IFlipper) gf.getGizmo(TYPE.LeftFlipper, new Vect(3,1));
		IBall ballFast = gf.getBall(topLeft, new Vect(4000,0));
		IBall ballSlow = gf.getBall(topLeft, new Vect(1000,0));
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
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IBall ballFast = gf.getBall(topLeft, new Vect(4000,0));
		IBall ballSlow = gf.getBall(topLeft, new Vect(1000,0));
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
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IBall ballFast = gf.getBall(topLeft, new Vect(4000,0));
		IBall ballSlow = gf.getBall(topLeft2, new Vect(-1000,0));
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
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IBall ballFast = gf.getBall(topLeft, new Vect(4000,0));
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
        IModel model = ModelFactory.getModel();
        GizmoFactory gf = new GizmoFactory(model);
		IBall ballFast = gf.getBall(topLeft, new Vect(4000,0));
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
