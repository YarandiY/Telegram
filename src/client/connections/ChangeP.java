package client.connections;


import client.main.Main;
import javafx.scene.control.Alert;
import shared.User;

import java.io.IOException;

public class ChangeP {
    public static void change(User newUser){
        try {
            System.out.println("omad inja");
            Main.outputStream.writeUTF("change");
            Main.outputStream.writeObject(newUser);
            Main.outputStream.flush();
            System.out.println("tamam");
        } catch (IOException e) {
            System.out.println("moteasefam");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Server not find!Try again");
            alert.show();
            Connect.connect();
            change(newUser);
        }
    }
}
