package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Absorber;
import model.BallGizmo;
import model.BoardFileHandler;
import model.GameModel;
import model.IBall;
import model.IGizmo;
import model.SquareGizmo;

public class FileHandlerTest {
	
	private GameModel model;
	private BoardFileHandler file;
	
	@Before
	public void setup() {
		model = new GameModel();
		file = new BoardFileHandler(model);
	}

	@Test
	public void testLoadSquare() {
		try {
			file.load("src/tests/test_file_square.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<IGizmo> gizmos = model.getGizmos();
		IGizmo square = null;
		
		for (IGizmo current : gizmos) {
			if (current instanceof SquareGizmo)
				square = current;
		}
		
		assertEquals(square.getID(), "S08");
	}

	@Test
	public void testLoadAbsorber() {
		try {
			file.load("src/tests/test_file_absorber.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<IGizmo> gizmos = model.getGizmos();
		IGizmo absorber = null;
		
		for (IGizmo current : gizmos) {
			if (current instanceof Absorber)
				absorber = current;
		}
		
		assertEquals(absorber.getID(), "A");
	}

	@Test
	public void testLoadBall() {
		try {
			file.load("src/tests/test_file_ball.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<IBall> balls = model.getBalls();
		IGizmo ball = null;
		
		for (IGizmo current : balls) {
			if (current instanceof BallGizmo)
				ball = current;
		}
		
		assertEquals(ball.getID(), "B");
	}
}
