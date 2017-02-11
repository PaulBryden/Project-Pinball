package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardFileHandler {

	public boolean save(GizmoList gizmos, String path) {
		try {
			BufferedWriter save = new BufferedWriter(new FileWriter(path));
			ArrayList<IGizmo> list = gizmos.returnGizmoList();
			ArrayList<String> connections = new ArrayList<>(); // connections to be made

			for (IGizmo current : list) {
				// TODO: Ensure that serializeGizmo() is providing correct output!
				save.write(current.serializeGizmo());

				// Record any connections for later
				ArrayList<IGizmo> connectedGizmos = current.getGizmosToTrigger();
				for (IGizmo destGizmo : connectedGizmos) {
					connections.add("Connect " + current.getID() + " " + destGizmo.getID());
				}

				// TODO: KeyConnections
			}

			// Finally, write connections to file
			for (String current : connections) {
				save.write(current);
			}

			save.close();

			return true; // Save successful, return true

		} catch (IOException e) {
			System.out.println("Error writing to file " + path);
			e.printStackTrace();
			return false;
		}
	}

	public GizmoList load(String path) {
		try {
			GizmoList gizmos = new GizmoList(); // This will be returned after reading
			ArrayList<String> connections = new ArrayList<>(); // Keeps track of connections from file

			BufferedReader load = new BufferedReader(new FileReader(path));
			String line = load.readLine();
			Scanner scan = null;

			while (line != null) {
				scan = new Scanner(line);
				String type = scan.next();

				if (type.equals("Connect") || type.equals("KeyConnect")) {
					connections.add(line); // Store connection info for later
				} else {
					createGizmo(type, scan, gizmos);
				}

				scan.close();
				line = load.readLine();
			}

			// Make connections here
			createConnections(connections, gizmos);

			load.close();

			return gizmos;

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + path);
			e.printStackTrace();
			return null;

		} catch (IOException e) {
			System.out.println("Error reading from file " + path);
			e.printStackTrace();
			return null;
		}
	}

	private void createGizmo(String type, Scanner scan, GizmoList gizmos) {
		IGizmo newGizmo = null;
		String id = null;
		double x1 = 0.0;
		double y1 = 0.0;

		// Only for Absorber
		double x2 = 0.0;
		double y2 = 0.0;

		// Velocity, only for Ball
		double xv = 0.0;
		double yv = 0.0;

		// Collect base info for gizmo (every gizmo will follow this starting format)
		id = scan.next();
		x1 = scan.nextDouble();
		y1 = scan.nextDouble();

		// Collect extra info for Absorber and Ball
		if (type.equals("Absorber")) {
			x2 = scan.nextDouble();
			y2 = scan.nextDouble();
		} else if (type.equals("Ball")) {
			xv = scan.nextDouble();
			yv = scan.nextDouble();
		}

		// TODO: Gizmo constructors to follow the following format
		// Should Absorber be a SquareGizmo, or its own gizmo class?
		try {
			switch (type) {
			case "Square":
				// newGizmo = SquareGizmo(id, x1, y1, null, null);
				break;
			case "Triangle":
				// newGizmo = TriangleGizmo(id, x1, y1);
				break;
			case "Circle":
				// newGizmo = CircleGizmo(id, x1, y1);
				break;
			case "LeftFlipper":
				// newGizmo = LeftFlipperGizmo(id, x1, y1);
				break;
			case "RightFlipper":
				// newGizmo = RightFlipperGizmo(id, x1, y1);
				break;
			case "Absorber":
//				newGizmo = SquareGizmo(id, x1, y1, x2, y2);
//				IAction action = new Action(); // absorber behaviour
//				newGizmo.addTriggerAction(action);
				break;
			case "Ball":
//				newGizmo = BallGizmo(id, x1, y1, xv, yv);
				break;
			default:
			}
		} catch (Exception e) {
			System.out.println("Error occurred when creating Gizmo");
			e.printStackTrace();
		}

		gizmos.addGizmo(newGizmo);
	}

	private void createConnections(ArrayList<String> connections, GizmoList gizmos) {
		Scanner scan = null;
		for (String current : connections) {
			scan = new Scanner(current);
			String type = scan.next();

			if (type.equals("Connect")) {
				String firstGizmo = scan.next();
				String secondGizmo = scan.next();

				// TODO: GizmoList function that creates connection between two gizmos in its list
				// gizmos.createConnection(firstGizmo, secondGizmo);

			} else if (type.equals("KeyConnect")) {
				String keyString = scan.next();
				String keyID = scan.next();
				String keyStatus = scan.next();
				String gizmoID = scan.next();

				// TODO: Functionality to create key connections!
			}

			scan.close();
		}
	}

}
