package tests;

import org.junit.Test;

import model.Absorber;
import model.BallGizmo;
import model.CircleGizmo;
import model.LeftFlipper;
import model.ModelFactory;
import model.RightFlipper;
import model.SquareGizmo;
import model.TriangleGizmo;
import physics.Vect;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;

public class GizmoTest {
	
	@Before
	public void setup(){
		
	}
	
	@Test
	public void AbsorberCreationTest() {
		Vect topLeft = new Vect(1,1);
		Vect bottomRight = new Vect(2,2); 
		Absorber newAbsorber = new Absorber(ModelFactory.getModel(),"A1",topLeft,bottomRight);
		
		assertEquals(newAbsorber.getBottomRightCoords(),bottomRight);
	}
	
	@Test
	public void TriangleCreationTest() {
		Vect topLeft = new Vect(1,1);
		TriangleGizmo triangle = new TriangleGizmo("T1",topLeft);
		
		assertEquals(triangle.getGridCoords(),topLeft);
	}
	
	@Test
	public void SquareCreationTest() {
		Vect topLeft = new Vect(1,1);
		SquareGizmo square = new SquareGizmo("S1",topLeft);
		
		assertEquals(square.getGridCoords(),topLeft);
	}
	
	@Test
	public void CircleCreationTest() {
		Vect topLeft = new Vect(1,1);
		CircleGizmo circle = new CircleGizmo("C1",topLeft);
		assertEquals(circle.getGridCoords(),topLeft);
	}
	
	@Test
	public void BallCreationTest() {
		Vect topLeft = new Vect(1,1);
		BallGizmo circle = new BallGizmo("B1",topLeft,topLeft);
		assertEquals(circle.getGridCoords(),topLeft);
	}
	
	
	@Test
	public void LeftFlipperCreationTest() {
		Vect topLeft = new Vect(1,1);
		LeftFlipper leftFlipper = new LeftFlipper("LF1",topLeft);
		assertEquals(leftFlipper.getGridCoords(),topLeft);
	}
	
	@Test
	public void RightFlipperCreationTest() {
		Vect topLeft = new Vect(1,1);
		RightFlipper rightFlipper = new RightFlipper("RF1",topLeft);
		assertEquals(rightFlipper.getGridCoords(),topLeft);
	}
	
	@Test
	public void TriangleMoveTest() {
		Vect topLeft = new Vect(1,1);
		TriangleGizmo triangle = new TriangleGizmo("T1",topLeft);
		Vect moveLocation = new Vect(4,4);
		triangle.setGridCoords(moveLocation);
		assertEquals(triangle.getGridCoords(),moveLocation);
	}
	
	@Test
	public void SquareMoveTest() {
		Vect topLeft = new Vect(1,1);
		SquareGizmo square = new SquareGizmo("S1",topLeft);
		Vect moveLocation = new Vect(4,4);
		square.setGridCoords(moveLocation);
		assertEquals(square.getGridCoords(),moveLocation);
	}
	
	@Test
	public void CircleMoveTest() {
		Vect topLeft = new Vect(1,1);
		CircleGizmo circle = new CircleGizmo("C1",topLeft);
		Vect moveLocation = new Vect(4,4);
		circle.setGridCoords(moveLocation);
		assertEquals(circle.getGridCoords(),moveLocation);
	}
	
	@Test
	public void BallMoveTest() {
		Vect topLeft = new Vect(1,1);
		BallGizmo circle = new BallGizmo("B1",topLeft,topLeft);
		Vect moveLocation = new Vect(4,4);
		circle.setGridCoords(moveLocation);
		assertEquals(circle.getGridCoords(),moveLocation);
	}
	
	
	@Test
	public void LeftFlipperMoveTest() {
		Vect topLeft = new Vect(1,1);
		LeftFlipper leftFlipper = new LeftFlipper("LF1",topLeft);
		Vect moveLocation = new Vect(4,4);
		leftFlipper.setGridCoords(moveLocation);
		assertEquals(leftFlipper.getGridCoords(),moveLocation);
	}
	
	@Test
	public void RightFlipperMoveTest() {
		Vect topLeft = new Vect(1,1);
		RightFlipper rightFlipper = new RightFlipper("RF1",topLeft);
		Vect moveLocation = new Vect(4,4);
		rightFlipper.setGridCoords(moveLocation);
		assertEquals(rightFlipper.getGridCoords(),moveLocation);
	}
	

	
	@After
	public void teardown(){
		
	}
}
