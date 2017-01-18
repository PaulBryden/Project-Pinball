package main;

import model.HorizontalLine;
import model.Model;
import model.VerticalLine;
import physics.LineSegment;
import view.RunGUI;

public class Main {

	public static void main(String[] args){
		Model model= new Model();
		
		model.setBallSpeed(200,200);
		
		model.addLine(new HorizontalLine(100,100,300));
		model.addLine(new HorizontalLine(100,200,300));
		model.addLine(new HorizontalLine(100,300,300));
		model.addLine(new HorizontalLine(100,400,300));
		
		RunGUI gui = new RunGUI(model);
		gui.createAndShowGUI();
	}
}
