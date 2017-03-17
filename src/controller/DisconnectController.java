package controller;

import view.MainWindow;

class DisconnectController {
    private MainWindow mainWindow;

    DisconnectController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start(){
        if(mainWindow.getModel().isClient()){
            mainWindow.getBoard().getModel().getClient().stopClient();
        } else if(mainWindow.getModel().getHost() != null) {
            mainWindow.getBoard().getModel().getHost().disconnect();
        }

        mainWindow.revalidate();
        mainWindow.repaint();
    }
}
