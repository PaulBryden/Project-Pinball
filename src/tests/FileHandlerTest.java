package tests;

import static org.junit.Assert.assertEquals;

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
	public void testLoadAbsorber() {
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
	public void testLoadBall() {
		file.load("src/tests/test_file_ball.txt");
		List<IBall> balls = model.getBalls();
		IGizmo ball = null;
		
		for (IGizmo current : balls) {
			if (current instanceof BallGizmo)
				ball = current;
		}
		
		assertEquals(ball.getID(), "B");
	}
}
