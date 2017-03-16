package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import model.IModel;
import view.AboutDialog;
import view.AbstractToolBar;
import view.ClientDialog;
import view.HostDialog;
import view.MainWindow;
import view.MenuBar;

public class PrimaryActionListener implements ActionListener {
	
	private MainWindow mainWindow;
	private List<MenuBar> menuBars;
	private List<AbstractToolBar> toolBars;
	private RunListener runListener;
	
	public PrimaryActionListener(MainWindow mainWindow, IModel model) {
		this.mainWindow = mainWindow;
		this.menuBars = new LinkedList<>();
		this.toolBars = new LinkedList<>();
		this.runListener = new RunListener(mainWindow, model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "load":
			new LoadBoardController(mainWindow).start();
			break;
		case "save":
			new SaveBoardController(mainWindow).start();
			break;
		case "toggle":
		case "build_mode":
		case "run_mode":
			pauseGame();
			new ModeToggleController(mainWindow).start();
			break;
		case "run":
			runListener.actionPerformed(e);
			enable("pause");
			disable("run");
			disable("tick");
			break;
		case "pause":
			pauseGame();
			break;
		case "tick":
			runListener.actionPerformed(e);
			break;
		case "host":
			pauseGame();
			new HostDialog(mainWindow);
			break;
		case "client":
			pauseGame();
			new ClientDialog(mainWindow);
			break;
		case "quit":
			exitApplication();
			break;
		case "add":
			new AddGizmoController(mainWindow).start();
			break;
		case "delete":
			new DeleteGizmoController(mainWindow).start();
			break;
		case "rotate":
			new RotateGizmoController(mainWindow).start();
			break;
		case "move":
			new MoveGizmoController(mainWindow).start();
			break;
		case "connect":
			new LinkGizmosController(mainWindow).start();
			break;
		case "key":
			new AddKeyTriggerController(mainWindow).start();
			break;
		case "settings":
			new BoardSettingsController(mainWindow).start();
			break;
		case "about":
			pauseGame();
			new AboutDialog(mainWindow);
			break;
		default:
			System.out.println(e.getActionCommand());
		}
	}
	
	public void pauseGame() {
		runListener.pause();
		enable("run");
		enable("tick");
		disable("pause");
	}
	
	private void exitApplication() {
		System.exit(0);
	}

	public void addMenuBar(MenuBar menuBar) {
		menuBars.add(menuBar);
	}
	
	public void addToolBar(AbstractToolBar toolBar) {
		toolBars.add(toolBar);
	}
	
	private void enable(String name) {
		for (MenuBar menuBar : menuBars) {
			// TODO enable menu item
		}
		for (AbstractToolBar toolBar : toolBars) {
			toolBar.enableButton(name);
		}
	}
	
	private void disable(String name) {
		for (MenuBar menuBar : menuBars) {
			// TODO disable menu item
		}
		for (AbstractToolBar toolBar : toolBars) {
			toolBar.disableButton(name);
		}
	}

}
