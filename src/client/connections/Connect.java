package client.connections;

import client.main.Main;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connect{

    public static  void connect(){
        try {
            Main.socket=new Socket("localhost",8080);
            Main.outputStream.close();
            Main.outputStream=new ObjectOutputStream(Main.socket.getOutputStream());
            Main.inputStream.close();
            Main.inputStream=new ObjectInputStream(Main.socket.getInputStream());
        } catch (IOException e) {
            System.out.println("again");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server not find!Try again");
            alert.show();
            connect();
        }
    }
}
