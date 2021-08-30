package client.main;

import client.controller.LoginPage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main extends Application {

    private static Stage ps;
    public static  Socket socket;
    public static  ObjectInputStream inputStream;
    public static  ObjectOutputStream outputStream;
    public static Class controler;

    public static Stage getPrimaryStage() {
        return ps;
    }

    @Override
    public void start(Stage primaryStage) {
        ps=primaryStage;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../UI/fxmlFiles/loginPage.fxml"));
            primaryStage.setScene(new Scene(root, 450, 600));
            primaryStage.setResizable(false);
            ps.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    try {
                        Platform.exit();
                        System.exit(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
                socket = new Socket("localhost", 8080);
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                inputStream = new ObjectInputStream(socket.getInputStream());
                controler= LoginPage.class.getClass();
        } catch (IOException e) {
            System.out.println("please check your connection");
        }finally {
            launch(args);
        }
    }
}
