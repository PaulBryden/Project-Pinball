package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;

import model.IModel;
import view.MainWindow;

public class ColourChangeController {

	private MainWindow mainWindow;
	private ActionEvent event;

	public ColourChangeController(MainWindow mainWindow, ActionEvent event) {
		this.mainWindow = mainWindow;
		this.event = event;
	}

	public void start() {
		IModel model = mainWindow.getModel();
		String actionCommand = event.getActionCommand();
		Color oldColour = null;
		String title = "";
		switch (actionCommand) {
		case "background":
			oldColour = model.getBackgroundColour();
			title = "Choose a background colour";
			break;
		case "text_colour":
			oldColour = model.getTextColour();
			title = "Choose a text colour";
			break;
		case "grid_colour":
			oldColour = mainWindow.getBoard().getGridColour();
			break;
		default: // do nothing
		}
		Color colour = JColorChooser.showDialog(mainWindow, title, oldColour);
		if (colour == null || colour.equals(oldColour))
			return;
		switch (actionCommand) {
		case "background":
			model.setBackgroundColour(colour);
			break;
		case "text_colour":
			model.setTextColour(colour);
			break;
		case "grid_colour":
			mainWindow.getBoard().setGridColour(colour);
			break;
		default: // do nothing
		}
		if (event.getSource() instanceof JButton) {
			JButton button = (JButton) event.getSource();
			button.setBackground(colour);
		}
		mainWindow.getBoard().reRender();
	}

}
