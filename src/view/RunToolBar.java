package view;

import controller.RunToolBarListener;
import model.IModel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.net.URL;

class RunToolBar extends JToolBar {
	
	private static final long serialVersionUID = 9159488944045570471L;

	private RunToolBarListener listener;
	
	private JButton loadButton;
	private JButton runButton;
	private JButton pauseButton;
	private JButton tickButton;
	private JButton buildModeButton;
	
    RunToolBar(MainWindow mainWindow, IModel model){
        super("Run Mode");

    	this.listener = new RunToolBarListener();
        
        loadButton = makeButton("load", "LOAD", "Load a board layout", "Load");
        this.add(loadButton);
        
        addSeparator();
        
        runButton = makeButton("run", "RUN", "Run game", "Run");
        this.add(runButton);
        
        pauseButton = makeButton("pause", "PAUSE", "Pause game", "Pause");
        this.add(pauseButton);
        
        tickButton = makeButton("tick", "TICK", "Tick for one frame", "Tick");
        this.add(tickButton);
        
        addSeparator();
        
        buildModeButton = makeButton("build_mode", "TOGGLE_MODE", "Enter build mode", "Build mode");
        this.add(buildModeButton);
    }
    
    private JButton makeButton(String icon, String actionCommand, String toolTip, String altText) {
    	String iconPath = "/icons/"+ icon + ".png";
    	URL iconURL = RunToolBar.class.getResource(iconPath);
    	JButton button = new JButton();
    	button.setActionCommand(actionCommand);
    	button.setToolTipText(toolTip);
    	button.addActionListener(listener);
    	if (iconURL != null) {
    		button.setIcon(new ImageIcon(iconURL, altText));
    	} else {
    		button.setText(altText);
    	}
    	return button;
    }

    void stop() {
        pauseButton.doClick();
    }
}
