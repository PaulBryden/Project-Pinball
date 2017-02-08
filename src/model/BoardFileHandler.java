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
			BufferedWriter save = new BufferedWriter(new FileWriter(path)); // Assuming
																			// extension
																			// exists
			ArrayList<IGizmo> list = gizmos.returnGizmoList();

			for (IGizmo current : list) {
				save.write(current.serializeGizmo());
				// TODO: KeyConnections
				// Ensure that serializeGizmo() is providing correct output!
			}

			save.close();

			return true; // Return true if successful

		} catch (IOException e) {
			System.out.println("Error writing to file " + path);
			e.printStackTrace();
			return false;
		}
	}

	public GizmoList load(String path) {
		try {
			GizmoList gizmos = new GizmoList(); // This will be returned after
												// reading
			// The following arraylist keeps tracks of connections that can't be
			// made immediately
			// when read from the file
			ArrayList<String> connections = new ArrayList<>();

			BufferedReader load = new BufferedReader(new FileReader(path));
			String line = load.readLine();
			Scanner scan = null;
			while (line != null) {
				scan = new Scanner(line);
				String type = scan.next();

				if (type != "Connect" && type != "KeyConnect") {
					IGizmo g = createGizmo(type, scan);
					gizmos.addGizmo(g);
				} else {
					// TODO: Handle Connect and KeyConnect
					// If one or both gizmos haven't yet been read, store connection info for later*
				}

				scan.close();
				line = load.readLine();
			}

			// *If any connections have yet to be made, make them here
			// This should be fine as all of the gizmos should exist by now

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

	private IGizmo createGizmo(String type, Scanner scan) {
//		try {
//			switch (type) {
//			case "Square":
//				IGizmoPhysics p = new LinearPhysics();
//				break;
//			case "Triangle":
//			case "Circle":
//			case "Ball":
//			case "Absorber":
//			case "LeftFlipper":
//			case "RightFlipper":
//			default:
//			}
//		} catch (Exception e) {
//			// TODO: Catch exceptions properly
//		}
		return null; // TEMP
	}
}
