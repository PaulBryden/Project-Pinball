package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.InputMismatchException;

import org.junit.Before;
import org.junit.Test;

import model.BoardFileHandler;
import model.GizmoFactory;
import model.IAbsorber;
import model.IBall;
import model.ICircleGizmo;
import model.IFlipper;
import model.IGizmo;
import model.IModel;
import model.ISquareGizmo;
import model.ITriangleGizmo;
import model.ModelFactory;
import model.GizmoFactory.TYPE;
import physics.Vect;

public class FileHandlerTest {
	
	private IModel model;
	private GizmoFactory gf;
	private BoardFileHandler file;
	
	@Before
	public void setup() {
        model = ModelFactory.getModel();
        gf = new GizmoFactory(model);
		file = new BoardFileHandler(model);
	}
	
	
	
	// LOADING TESTS
	
	@Test(expected=IOException.class)
	public void testLoadInvalidFile() throws IOException {
		file.load("non_existant_path");
	}
	
	@Test
	public void testLoadEmptyFile() throws IOException {
		file.load("src/tests/test_file_empty.txt");
	}
	
	@Test(expected=IOException.class)
	public void testLoadEmptyPath() throws IOException {
		file.load("");
	}
	
	@Test(expected=InputMismatchException.class)
	public void testLoadErroneousFile() throws IOException {
		file.load("src/tests/test_file_erroneous.txt");
	}
	
	@Test
	public void testLoadSpecFile() throws IOException {
		file.load("savefiles/spec_save_file.txt");
	}
	
	
	
	// LOADING - INDIVIDUAL GIZMOS

	@Test
	public void testLoadSquareID() throws IOException {
		file.load("src/tests/test_file_square.txt");
		List<IGizmo> gizmos = model.getGizmos();
		IGizmo square = null;
		
		for (IGizmo current : gizmos) {
			if (current instanceof ISquareGizmo)
				square = current;
		}
		
		assertEquals(square.getID(), "S08");
	}

	@Test
	public void testLoadAbsorberID() throws IOException {
		file.load("src/tests/test_file_absorber.txt");
		List<IGizmo> gizmos = model.getGizmos();
		IGizmo absorber = null;
		
		for (IGizmo current : gizmos) {
			if (current instanceof IAbsorber)
				absorber = current;
		}
		
		assertEquals(absorber.getID(), "A");
	}

	@Test
	public void testLoadBallID() throws IOException {
		file.load("src/tests/test_file_ball.txt");
		List<IBall> balls = model.getBalls();
		IBall ball = null;
		
		for (IBall current : balls) {
			if (current instanceof IBall)
				ball = current;
		}
		
		assertEquals(ball.getID(), "B");
	}
	
	@Test
	public void testLoadCircleID() throws IOException {
		file.load("src/tests/test_file_circle.txt");
		List<IGizmo> gizmos = model.getGizmos();
		IGizmo circle = null;
		
		for (IGizmo current : gizmos) {
			if (current instanceof ICircleGizmo)
				circle = current;
		}
		
		assertEquals(circle.getID(), "C157");
	}

	@Test
	public void testLoadTriangleID() throws IOException {
		file.load("src/tests/test_file_triangle.txt");
		List<IGizmo> gizmos = model.getGizmos();
		IGizmo triangle = null;
		
		for (IGizmo current : gizmos) {
			if (current instanceof ITriangleGizmo)
				triangle = current;
		}
		
		assertEquals(triangle.getID(), "T72");
	}

	@Test
	public void testLoadLeftFlipperID() throws IOException {
		file.load("src/tests/test_file_left_flipper.txt");
		List<IGizmo> gizmos = model.getGizmos();
		IGizmo flipper = null;
		
		for (IGizmo current : gizmos) {
			if (current instanceof IFlipper)
				flipper = current;
		}
		
		assertEquals(flipper.getID(), "LF195");
	}

	@Test
	public void testLoadRightFlipperID() throws IOException {
		file.load("src/tests/test_file_right_flipper.txt");
		List<IGizmo> gizmos = model.getGizmos();
		IGizmo flipper = null;
		
		for (IGizmo current : gizmos) {
			if (current instanceof IFlipper)
				flipper = current;
		}
		
		assertEquals(flipper.getID(), "RF182");
	}
	
	
	
	// SAVING TESTS

	@Test(expected=IOException.class)
	public void testSaveEmptyPath() throws IOException {
		// Should throw IOException
		file.save("");
	}
	
	@Test
	public void testSaveEmptyModel() throws IOException {
		// Should produce file with only Gravity and Friction
		file.save("test_save_file.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		int count = 0;
		String line = null;
		
		while ((line = br.readLine()) != null)
			count++;

		assertEquals(count, 2);

		br.close();
	}
	
	

	// SAVING - INDIVIDUAL GIZMOS
	
	@Test
	public void testSaveSquare() throws IOException {
		IGizmo gizmo = gf.getGizmo(TYPE.Square, new Vect(0, 8));
		model.addGizmo(gizmo);
		file.save("test_save_file.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		
		assertTrue(line.equals("Square S0008 0 8"));
	}
	
	@Test
	public void testSaveCircle() throws IOException {
		IGizmo gizmo = gf.getGizmo(TYPE.Circle, new Vect(4, 5));
		model.addGizmo(gizmo);
		file.save("test_save_file.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		
		assertTrue(line.equals("Circle C0405 4 5"));
	}

	@Test
	public void testSaveTriangle() throws IOException {
		IGizmo gizmo = gf.getGizmo(TYPE.Triangle, new Vect(10, 5));
		model.addGizmo(gizmo);
		file.save("test_save_file.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		
		assertTrue(line.equals("Triangle T1005 10 5"));
	}
	
	@Test
	public void testSaveBall() throws IOException {
		GizmoFactory gf = new GizmoFactory(model);
		IBall ball = gf.getBall(new Vect(10, 14), new Vect(0, 0));
		model.addBall(ball);
		file.save("test_save_file.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		assertTrue(line.equals("Ball B00 10.0000 14.0000 0.0000 0.0000"));
	}
	
	@Test
	public void testSaveAbsorber() throws IOException {
        GizmoFactory gf = new GizmoFactory(model);
		IAbsorber gizmo = gf.getAbsorber(new Vect(5, 5), new Vect(15, 15));
		model.addGizmo(gizmo);
		file.save("test_save_file.txt");

		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		
		assertTrue(line.equals("Absorber A0505 5 5 15 15"));
	}
	
	@Test
	public void testSaveLeftFlipper() throws IOException {
		IGizmo gizmo = gf.getGizmo(TYPE.LeftFlipper, new Vect(7, 9));
		model.addGizmo(gizmo);
		file.save("test_save_file.txt");

		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		
		assertTrue(line.equals("LeftFlipper LF0709 7 9"));
	}

	@Test
	public void testSaveRightFlipper() throws IOException {
		IGizmo gizmo = gf.getGizmo(TYPE.RightFlipper, new Vect(14, 1));
		model.addGizmo(gizmo);
		file.save("test_save_file.txt");

		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		
		assertTrue(line.equals("RightFlipper RF1401 14 1"));
	}

}
