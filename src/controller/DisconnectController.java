package controller;

import model.IModel;
import view.MainWindow;

class DisconnectController {
    private MainWindow mainWindow;

    DisconnectController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start(){
        IModel model = mainWindow.getModel();

        if(model.isClient()) {
            model.getClient().stopClient();
            mainWindow.setStatusLabel("Disconnected from host.");
            mainWindow.toggleView();
        } else if(model.getHost() != null) {
            model.getHost().disconnect();
            mainWindow.setStatusLabel("Disconnected from clients, no longer hosting.");
            mainWindow.toggleView();
        } else {
            mainWindow.setStatusLabel("You are already disconnected.");
        }
    }
}
