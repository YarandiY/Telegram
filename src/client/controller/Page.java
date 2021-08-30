package client.controller;

import client.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Page {

     public static void goTo(URL location){
        Stage stage = Main.getPrimaryStage();
        Parent root=null;
        try
        {
            root = FXMLLoader.load(location);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.show();
    }
}
