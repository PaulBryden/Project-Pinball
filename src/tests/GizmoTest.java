package tests;

import org.junit.Test;

import model.GizmoFactory;
import model.IAbsorber;
import model.IBall;
import model.IFlipper;
import model.IGizmo;
import model.ModelFactory;
import model.GizmoFactory.TYPE;
import physics.Vect;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

public class GizmoTest {
	
	private GizmoFactory gf;
	
	@Before
	public void setup(){
		gf = new GizmoFactory(ModelFactory.getModel());
	}
	
	@Test
	public void AbsorberCreationTest() {
		Vect topLeft = new Vect(1,1);
		Vect bottomRight = new Vect(2,2);
		IAbsorber gizmo = (IAbsorber) gf.getRectangularGizmo(TYPE.Absorber, topLeft,bottomRight);
		assertEquals(gizmo.getBottomRightCoords(),bottomRight);
	}
	
	@Test
	public void TriangleCreationTest() {
		Vect topLeft = new Vect(1,1);
		IGizmo triangle = gf.getGizmo(TYPE.Triangle, topLeft);
		
		assertEquals(triangle.getGridCoords(),topLeft);
	}
	
	@Test
	public void SquareCreationTest() {
		Vect topLeft = new Vect(1,1);
		IGizmo square = gf.getGizmo(TYPE.Square, topLeft);
		
		assertEquals(square.getGridCoords(),topLeft);
	}
	
	@Test
	public void CircleCreationTest() {
		Vect topLeft = new Vect(1,1);
		IGizmo circle = gf.getGizmo(TYPE.Circle, topLeft);
		assertEquals(circle.getGridCoords(),topLeft);
	}
	
	@Test
	public void BallCreationTest() {
		Vect topLeft = new Vect(1,1);
		IBall circle = gf.getBall(topLeft,topLeft);
		assertEquals(circle.getGridCoords(),topLeft);
	}
	
	
	@Test
	public void LeftFlipperCreationTest() {
		Vect topLeft = new Vect(1,1);
		IFlipper leftFlipper = (IFlipper) gf.getGizmo(TYPE.LeftFlipper, topLeft);
		assertEquals(leftFlipper.getGridCoords(),topLeft);
	}
	
	@Test
	public void RightFlipperCreationTest() {
		Vect topLeft = new Vect(1,1);
		IFlipper rightFlipper = (IFlipper) gf.getGizmo(TYPE.RightFlipper, topLeft);
		assertEquals(rightFlipper.getGridCoords(),topLeft);
	}
	
	@Test
	public void TriangleMoveTest() {
		Vect topLeft = new Vect(1,1);
		IGizmo triangle = gf.getGizmo(TYPE.Triangle, topLeft);
		Vect moveLocation = new Vect(4,4);
		triangle.setGridCoords(moveLocation);
		assertEquals(triangle.getGridCoords(),moveLocation);
	}
	
	@Test
	public void SquareMoveTest() {
		Vect topLeft = new Vect(1,1);
		IGizmo square = gf.getGizmo(TYPE.Square, topLeft);
		Vect moveLocation = new Vect(4,4);
		square.setGridCoords(moveLocation);
		assertEquals(square.getGridCoords(),moveLocation);
	}
	
	@Test
	public void CircleMoveTest() {
		Vect topLeft = new Vect(1,1);
		IGizmo circle = gf.getGizmo(TYPE.Circle, topLeft);
		Vect moveLocation = new Vect(4,4);
		circle.setGridCoords(moveLocation);
		assertEquals(circle.getGridCoords(),moveLocation);
	}
	
	@Test
	public void BallMoveTest() {
		Vect topLeft = new Vect(1,1);
		GizmoFactory gf = new GizmoFactory(ModelFactory.getModel());
		IBall circle = gf.getBall(topLeft,topLeft);
		Vect moveLocation = new Vect(4,4);
		circle.setGridCoords(moveLocation);
		assertEquals(circle.getGridCoords(),moveLocation);
	}
	
	
	@Test
	public void LeftFlipperMoveTest() {
		Vect topLeft = new Vect(1,1);
		IFlipper leftFlipper = (IFlipper) gf.getGizmo(TYPE.LeftFlipper, topLeft);
		Vect moveLocation = new Vect(4,4);
		leftFlipper.setGridCoords(moveLocation);
		assertEquals(leftFlipper.getGridCoords(),moveLocation);
	}
	
	@Test
	public void RightFlipperMoveTest() {
		Vect topLeft = new Vect(1,1);
		IFlipper rightFlipper = (IFlipper) gf.getGizmo(TYPE.RightFlipper, topLeft);
		Vect moveLocation = new Vect(4,4);
		rightFlipper.setGridCoords(moveLocation);
		assertEquals(rightFlipper.getGridCoords(),moveLocation);
	}
	

	
	@After
	public void teardown(){
		
	}
}
