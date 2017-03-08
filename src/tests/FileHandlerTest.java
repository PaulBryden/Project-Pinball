package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.InputMismatchException;

import org.junit.Before;
import org.junit.Test;

import model.Absorber;
import model.BallGizmo;
import model.BoardFileHandler;
import model.CircleGizmo;
import model.GameModel;
import model.IBall;
import model.IGizmo;
import model.SquareGizmo;
import model.TriangleGizmo;

public class FileHandlerTest {
	
	private GameModel model;
	private BoardFileHandler file;
	
	@Before
	public void setup() {
		model = new GameModel();
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
		file.load("spec_save_file.txt");
	}

	@Test
	public void testLoadSquareID() throws IOException {
		file.load("src/tests/test_file_square.txt");
		List<IGizmo> gizmos = model.getGizmos();
		IGizmo square = null;
		
		for (IGizmo current : gizmos) {
			if (current instanceof SquareGizmo)
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
			if (current instanceof Absorber)
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
			if (current instanceof BallGizmo)
				ball = current;
		}
		
		assertEquals(ball.getID(), "B");
	}
	
	
	
	// SAVING TESTS

	@Test(expected=IOException.class)
	public void testSaveEmptyPath() throws IOException {
		// Should throw IOException
		file.save("");
	}
	
	@Test
	public void testSaveEmptyModel() throws IOException {
		// Should produce an empty file
		file.save("test_save_file.txt");
		
		File testFile = new File("test_save_file.txt");
		assertEquals(testFile.length(), 0);
	}
	
	@Test
	public void testSaveSquare() throws IOException {
		IGizmo gizmo = new SquareGizmo("S08", 0, 8);
		model.addGizmo(gizmo);
		file.save("test_save_file.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		
		assertTrue(line.equals("Square S08 0 8"));
	}
	
	@Test
	public void testSaveCircle() throws IOException {
		IGizmo gizmo = new CircleGizmo("C45", 4, 5);
		model.addGizmo(gizmo);
		file.save("test_save_file.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		
		assertTrue(line.equals("Circle C45 4 5"));
	}

	@Test
	public void testSaveTriangle() throws IOException {
		IGizmo gizmo = new TriangleGizmo("T105", 10, 5);
		model.addGizmo(gizmo);
		file.save("test_save_file.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		
		assertTrue(line.equals("Triangle T105 10 5"));
	}
	
	@Test
	public void testSaveBall() throws IOException {
		IBall ball = new BallGizmo("B", 10, 14, 0, 0);
		model.addBall(ball);
		file.save("test_save_file.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		
		assertTrue(line.equals("Ball B 10.0 14.0 0.0 0.0"));
	}
	
	@Test
	public void testSaveAbsorber() throws IOException {
		IGizmo gizmo = new Absorber(model, "A", 5, 5, 15, 15);
		model.addGizmo(gizmo);
		file.save("test_save_file.txt");

		BufferedReader br = new BufferedReader(new FileReader("test_save_file.txt"));
		String line = br.readLine();
		br.close();
		
		assertTrue(line.equals("Absorber A 5 5 15 15"));
	}
}
