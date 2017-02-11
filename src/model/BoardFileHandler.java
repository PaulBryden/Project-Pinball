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
//				TODO: Ensure that serializeGizmo() is providing correct output!
				save.write(current.serializeGizmo());
				
				// Record any connections for later
				ArrayList<IGizmo> connectedGizmos = current.getGizmosToTrigger();
				for (IGizmo destGizmo : connectedGizmos) {
					connections.add("Connect " + current.getID() + " " + destGizmo.getID());
				}

//				TODO: KeyConnections
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
					gizmos.addGizmo(createGizmo(type, scan));
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

	private IGizmo createGizmo(String type, Scanner scan) {
		IGizmo gizmo = null;
		
		// TODO: All of these
		try {
			switch (type) {
			case "Square":
				break;
			case "Triangle":
				break;
			case "Circle":
				break;
			case "Ball":
				break;
			case "Absorber":
				break;
			case "LeftFlipper":
				break;
			case "RightFlipper":
				break;
			default:
			}
		} catch (Exception e) {
			System.out.println("Error occurred when creating Gizmo");
			e.printStackTrace();
		}

		return gizmo;
	}
	
	private void createConnections(ArrayList<String> connections, GizmoList gizmos) {
		Scanner scan = null;
		for (String current : connections) {
			scan = new Scanner(current);
			String type = scan.next();
			
			if (type.equals("Connect")) {
				String firstGizmo = scan.next();
				String secondGizmo = scan.next();

//				TODO: GizmoList function that creates connection between two gizmos in its list
//				gizmos.createConnection(firstGizmo, secondGizmo);
				
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
