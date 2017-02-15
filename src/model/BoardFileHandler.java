package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardFileHandler {

	public boolean save(List<IGizmo> gizmos, String path) {
		try {
			BufferedWriter save = new BufferedWriter(new FileWriter(path));
			List<IGizmo> list = gizmos;
			List<String> connections = new ArrayList<>(); // connections to be made

			for (IGizmo current : list) {
				// TODO: Ensure that serializeGizmo() is providing correct output!
				save.write(current.serializeGizmo());

				// Record any connections for later
				List<IGizmo> connectedGizmos = current.getGizmosToTrigger();
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

	public List<IGizmo> load(String path) {
		try {
			List<IGizmo> gizmos = new ArrayList<>(); // This will be returned after reading
			List<String> connections = new ArrayList<>(); // Keeps track of connections from file

			BufferedReader load = new BufferedReader(new FileReader(path));
			String line = load.readLine();
			Scanner scan = null;

			while (line != null) {
				scan = new Scanner(line);
				String type = scan.next();

				// FIXME: These if statements are a bit ugly
				if (type.equals("Connect") || type.equals("KeyConnect")) {
					connections.add(line); // Store connection info for later
				} else if (type.equals("Move") || type.equals("Rotate") || type.equals("Delete")) {
					executeOperation(type, scan, gizmos); // Build mode operation
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

	private void createGizmo(String type, Scanner scan, List<IGizmo> gizmos) {
		IGizmo newGizmo = null;

		// Collect base info for gizmo (every gizmo will follow this starting format)
		String id = scan.next();
		double x1 = scan.nextDouble();
		double y1 = scan.nextDouble();

		// TODO: Gizmo constructors to follow the following format
		// Should Absorber be a SquareGizmo, or its own gizmo class?
		// ^^Absorber should be a square gizmo with absorber action.
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
				double x2 = scan.nextDouble();
				double y2 = scan.nextDouble();
				// newGizmo = SquareGizmo(id, x1, y1, x2, y2);
				// IAction action = new Action(); // absorber behaviour
				// newGizmo.addTriggerAction(action);
				break;
			case "Ball":
				double xv = scan.nextDouble();
				double yv = scan.nextDouble();
				// newGizmo = BallGizmo(id, x1, y1, xv, yv);
				break;
			default:
			}
		} catch (Exception e) {
			System.out.println("Error occurred when creating Gizmo");
			e.printStackTrace();
		}

		gizmos.add(newGizmo);
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
				String keyString = scan.next();
				String keyID = scan.next();
				String keyStatus = scan.next();
				String gizmoID = scan.next();

				// TODO: Functionality to create key connections!
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
			// Move Gizmo to new co-ords
			break;
		case "Rotate":
			// Rotate Gizmo 90 clockwise
			break;
		case "Delete":
			// Delete Gizmo
			break;
		default:
		}
	}

}
