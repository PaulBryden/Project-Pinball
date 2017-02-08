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

	public boolean save(GizmoList gizmos, String path){
		try {
			BufferedWriter save = new BufferedWriter(new FileWriter(path)); // Assuming extension exists
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

	public GizmoList load(String path){
		try {
			GizmoList gizmos = new GizmoList(); // This will be returned after reading
			// The following arraylist keeps tracks of connections that can't be made immediately
			// when read from the file
			ArrayList<String> connections = new ArrayList<>();
			
			BufferedReader load = new BufferedReader(new FileReader(path));
			String line = load.readLine();
			Scanner scan = null;
			while (line != null) {
				scan = new Scanner(line);
				String type = scan.next();
				
				int id = 0;
				double x = 0, y = 0, xVel = 0, yVel = 0;
				if (type != "Connect" && type != "KeyConnect") {
					// Only collect these if type is a gizmo
					id = scan.nextInt(); // TODO: Check if this should be a string
					x = scan.nextDouble();
					y = scan.nextDouble();
					xVel = scan.nextDouble();
					yVel = scan.nextDouble();
				} else {
					// TODO: Handle Connect and KeyConnect
				}
				
				// TODO: Put this in it's own method
				IGizmo g = null;
				try {
					switch (type) {
					case "Square" :
						IGizmoPhysics p = new LinearPhysics();
						g = new SquareGizmo(null, xVel, yVel, p, id);
						gizmos.addGizmo(g);
						break;
					case "Triangle" :
					case "Circle" :
					case "Ball" :
					case "Absorber" :
					case "LeftFlipper" :
					case "RightFlipper" :
					default :
					}
				} catch (Exception e) {
					// TODO: Catch exceptions properly
				}
				
				// TODO
				// Instead, if line contains connection, make that connection between existing gizmos
				// If one or both gizmos haven't yet been read, store connection info for later*
				
				scan.close();
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
}
