package project.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import project.App;

public abstract  class Controller {
    public abstract void init(Object parameters);


    public void changeView(String path,Object parameters) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource(path));
            Parent root =  loader.load();
            Controller c = loader.getController();
            c.init(parameters);
            App.getAppScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
