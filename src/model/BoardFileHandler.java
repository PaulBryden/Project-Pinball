package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import physics.Vect;

public class BoardFileHandler {
	
	private IModel model;
	
	public BoardFileHandler(IModel model) {
		this.model = model;
	}

	public void save(String path) throws IOException {
			BufferedWriter save = new BufferedWriter(new FileWriter(path));
			List<IGizmo> list = model.getGizmos();

			for (IGizmo current : list) {
				if (!(current instanceof Wall)) {
					save.write(current.serializeGizmo()); // Also contains connection info
				}
			}
			
			// Write balls to file
			List<IBall> balls = model.getBalls();
			for (IBall current : balls) {
				save.write(current.serializeGizmo());
			}

			// Finally, write key connections to file
			Map<Integer, ITrigger> keyPressedTriggers = model.getKeyPressedTriggers();
			Map<Integer, ITrigger> keyReleasedTriggers = model.getKeyReleasedTriggers();

			for (Map.Entry<Integer, ITrigger> current : keyPressedTriggers.entrySet()) {
				Set<IGizmo> gizmosToTrigger = current.getValue().getGizmosToTrigger();
				for (IGizmo gizmo : gizmosToTrigger) {
					save.write("KeyConnect key " + current.getKey() + " down " + gizmo.getID() + "\n");
				}
			}

			for (Map.Entry<Integer, ITrigger> current : keyReleasedTriggers.entrySet()) {
				Set<IGizmo> gizmosToTrigger = current.getValue().getGizmosToTrigger();
				for (IGizmo gizmo : gizmosToTrigger) {
					save.write("KeyConnect key " + current.getKey() + " up " + gizmo.getID() + "\n");
				}
			}

			save.close();

			System.out.println("Save file written successfully");

	}

	public void load(String path) throws IOException {
			List<IGizmo> gizmos = new ArrayList<>(); // This will be returned after reading
			List<String> connections = new ArrayList<>(); // Keeps track of connections from file

			BufferedReader load = new BufferedReader(new FileReader(path));
			String line = load.readLine();
			Scanner scan = null;

			while (line != null) {
				if (!line.isEmpty()) {
					scan = new Scanner(line);
					String type = scan.next();

					if (type.equals("Connect") || type.equals("KeyConnect")) {
						connections.add(line); // Store connection info for later
					} else if (type.equals("Move") || type.equals("Rotate") || type.equals("Delete")) {
						executeOperation(type, scan, gizmos); // Build mode operation
					} else {
						IGizmo gizmo = createGizmo(type, scan, gizmos);
						if (gizmo instanceof IBall) {
							model.addBall((IBall) gizmo);
						} else {
							model.addGizmo(gizmo);
						}
					}

					scan.close();
				}

				line = load.readLine();
			}

			// Create connections after all gizmos have been loaded
			createConnections(connections, gizmos);

			load.close();

	}

	private IGizmo createGizmo(String type, Scanner scan, List<IGizmo> gizmos) {
		IGizmo newGizmo = null;
		int x1 = 0;
		int y1 = 0;

		// Ball uses doubles for its co-ords
		double ballx1 = 0.0;
		double bally1 = 0.0;

		// Collect base info for gizmo (every gizmo will follow this starting format)
		String id = scan.next();

		if (type.equals("Ball")) {
			ballx1 = scan.nextDouble();
			bally1 = scan.nextDouble();
		} else {
			x1 = scan.nextInt();
			y1 = scan.nextInt();
		}

		try {
			switch (type) {
			case "Square":
				newGizmo = new SquareGizmo(id, x1, y1);
				break;
			case "Triangle":
				newGizmo = new TriangleGizmo(id, x1, y1);
				break;
			case "Circle":
				newGizmo = new CircleGizmo(id, x1, y1);
				break;
			case "LeftFlipper":
				newGizmo = new LeftFlipper(id, x1, y1);
				break;
			case "RightFlipper":
				newGizmo = new RightFlipper(id, x1, y1);
				break;
			case "Absorber":
				int x2 = scan.nextInt();
				int y2 = scan.nextInt();
				List<IBall> balls = new ArrayList<>();
				newGizmo = new Absorber(id, x1, y1, x2, y2, balls);
				break;
			case "Ball":
				double xv = scan.nextDouble();
				double yv = scan.nextDouble();
				newGizmo = new BallGizmo(id, ballx1, bally1, xv, yv);
				break;
			default:
				System.out.println("No gizmo created");
			}
		} catch (Exception e) {
			System.out.println("Error occurred when creating Gizmo");
			e.printStackTrace();
		}

		gizmos.add(newGizmo);
		return newGizmo;
	}

	private void createConnections(List<String> connections, List<IGizmo> gizmos) {
		Scanner scan = null;
		for (String current : connections) {
			scan = new Scanner(current);
			String type = scan.next();

			if (type.equals("Connect")) {
				String firstGizmo = scan.next();
				String secondGizmo = scan.next();

				if (firstGizmo != null && secondGizmo != null)
					findGizmoByID(gizmos, firstGizmo).addGizmoToTrigger(findGizmoByID(gizmos, secondGizmo));
				else {
					System.out.println("Error connecting gizmos!");
					System.out.println("Gizmo 1: " + firstGizmo + ", Gizmo 2: " + secondGizmo);
				}

			} else if (type.equals("KeyConnect")) {
				String keyString = scan.next(); // Won't be used
				int keyID = scan.nextInt();
				String keyStatus = scan.next();
				String gizmoID = scan.next();
				IGizmo gizmo = findGizmoByID(gizmos, gizmoID);
				if (keyStatus.equals("down"))
					model.addKeyPressedTrigger(keyID, gizmo);
				else if (keyStatus.equals("up"))
					model.addKeyReleasedTrigger(keyID, gizmo);
			}

			scan.close();
		}
	}

	private IGizmo findGizmoByID(List<IGizmo> gizmos, String gizmoID) {
		for (int i = 0; i < gizmos.size(); i++) {
			if (gizmos.get(i).getID().equals(gizmoID))
				return gizmos.get(i);
		}

		return null;
	}

	private void executeOperation(String type, Scanner scan, List<IGizmo> gizmos) {
		String id = scan.next();

		switch (type) {
		case "Move":
			double x = scan.nextDouble();
			double y = scan.nextDouble();
			Vect newCoords = new Vect(x, y);
			findGizmoByID(gizmos, id).setGridCoords(newCoords);
			break;

		case "Rotate":
			findGizmoByID(gizmos, id).rotate(1);
			break;

		case "Delete":
			gizmos.remove(findGizmoByID(gizmos, id));
			break;

		default:
			System.out.println("No operations applied");
		}
	}

}
